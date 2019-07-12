package hw2;


//* do tomorrow create top array full of 0 and bottom array full of 1 */
public class Percolation {

    // local variable to hold values of a grid
    WeightedQuickUnionUF wUnion;
    private boolean[] sites;
    private int nSites;
    private int nElements;
    private int nElementsPerRow;
    private int virtualTop=0;
    private int virtualBottom;
    private int nVirtualTopElements;

    /** create N-by-N grid, with all the sites initial */
    public Percolation(int n) {
        validate(n);
        nVirtualTopElements = (n*n)-n;
        wUnion = new WeightedQuickUnionUF(n*n+nVirtualTopElements+1);
        sites = new boolean[n*n];
        nElementsPerRow = n;
        createVirtualElement();
        nElements = n*n+nVirtualTopElements+1;
        virtualBottom = nElements - 1;
    }

    /** connect all the top elements (0 up to N) to virtual top */
    private void createVirtualElement() {
        for (int i =0; i<nVirtualTopElements;i++) {
            wUnion.union(0,i);
            int val = (nVirtualTopElements+i)%(nVirtualTopElements+nElementsPerRow);
            wUnion.union(i,val);
        }
    }

    /** validate if n is greater than 0 */
    private void validate(int n) {
        if (n<=0) {
            throw new IllegalArgumentException("N should be greater than 0");
        }
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return nSites;
    }


    /** open the site (row,col) if it is not open */
    public void open(int row, int col) {
        if (!isOpen(row,col)) {
            int pos = xyTo1D(row,col);
            sites[pos-nVirtualTopElements] = true;
            nSites+=1;
            connectNeighbour(row,col);
            if (isBottomElement(pos)) {
                if (wUnion.find(nElements-1) != virtualBottom) {
                    if (!checkIfItemIsLastandHasNeighbour(row,col)) {
                        return;
                    }
                } else {
                    wUnion.union(pos,virtualBottom);
                }
            }
        }
    }

    public boolean checkIfItemIsLastandHasNeighbour(int row,int col) {
        return isFull(row-1,col) || isFull(row+1,col) || isFull(row,col-1) || isFull(row,col+1);
    }

    public boolean isBottomElement(int val) {
        return val>= nElements-1-nElementsPerRow;
    }


    /** is the site (row,col) full*/
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= nElementsPerRow || col >= nElementsPerRow) {
            return false;
        }
        if (!isOpen(row,col)) {
            return false;
        }
        int pos = xyTo1D(row,col);
        int x = wUnion.find(pos);
        return x==virtualTop;
    }

    /** is the site (row,col) open */
    public boolean isOpen(int row,int col) {
        if (row < 0 || col < 0 || row >= nElementsPerRow || col >= nElementsPerRow) {
            return false;
        }
        int pos = xyTo1D(row,col);
        return sites[pos-nVirtualTopElements];
    }

    /** does the system percolates */
    public boolean percolates() {
        return wUnion.find(nElements-1) == virtualTop;
    }

    /** helper method to check if a site has neightbours
     * if yes connect them
     * @param row
     * @param col
     */
    public void connectNeighbour(int row, int col) {
        int x = xyTo1D(row,col);

        // check top
        if (isOpen(row-1,col)) {
            int y = xyTo1D(row-1,col);
            wUnion.union(x,y);
        }

        // check bottom
        if (isOpen(row+1,col)) {
            int y = xyTo1D(row+1,col);
            wUnion.union(x,y);
        }

        // check left
        if (isOpen(row,col-1)) {
            int y = xyTo1D(row,col-1);
            wUnion.union(x,y);
        }

        // check right
        if (isOpen(row,col+1)) {
            int y = xyTo1D(row,col+1);
            wUnion.union(x,y);
        }
    }

    public int getSpacing() {
        return nVirtualTopElements/nElementsPerRow;
    }


    /** convert row and col into 1d position in the array */
    public int xyTo1D(int row, int col) {
        int spacing = getSpacing();
        int val = ((row+spacing) * nElementsPerRow) + col;
        return val;
    }

}
