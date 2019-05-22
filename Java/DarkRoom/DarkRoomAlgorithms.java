/* 
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 * 
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

// TODO: comment this file explaining its behavior

import acm.graphics.*;

public class DarkRoomAlgorithms implements DarkRoomAlgorithmsInterface {

	public GImage rotateLeft(GImage source) {
		
		int [][] pixels = source.getPixelArray();
		
		
		int [][] rotateLeftPixels = new int [pixels.length][pixels[0].length]; 
		
		for (int row =0; row<pixels.length; row++) {
			for (int column=0; column<pixels[row].length; column++) {
				rotateLeftPixels[row][column] = pixels[column][row];
				
			}
		}
		
		
		GImage rotateLeftImage = new GImage(rotateLeftPixels);
		return rotateLeftImage;
	}

	public GImage rotateRight(GImage source) {
		
		int [][] pixels = source.getPixelArray();
		
		
		int [][] rotateRightPixels = new int [pixels.length][pixels[0].length]; 
		
		
		for (int row =0; row<pixels.length; row++) {
			for (int col=0; col<pixels[row].length; col++) {	
				rotateRightPixels[col][pixels.length - row-1]   = pixels[row][col]; 	
				
			}
		}
		
		
		GImage rotateRightImage = new GImage(rotateRightPixels);
		return rotateRightImage;
	}

	public GImage flipHorizontal(GImage source) {
		
		int [][] pixels = source.getPixelArray();
		int [][] flipHorizontalPixels = new int [pixels.length][pixels[0].length]; 
		
		for (int row =0; row<pixels.length; row++) {
			for (int col=0; col<pixels[row].length; col++) {				                         
				flipHorizontalPixels[row][pixels[0].length-col-1]   = pixels[row][col]; 	
			}
		}
		
		
		GImage flipHorizontal = new GImage(flipHorizontalPixels);
		return flipHorizontal;
		
	}

	public GImage negative(GImage source) {
		
		int [][] pixels = source.getPixelArray();
		int [][] negativePixels = new int [pixels.length][pixels[0].length]; 
		
		for (int row =0; row<pixels.length; row++) {
			for (int col=0; col<pixels[row].length; col++) {				                         


				int pixel = pixels[row][col];
				
				int redValue = GImage.getRed(pixel);
				int greenValue = GImage.getGreen(pixel);
				int blueValue = GImage.getBlue(pixel);
				
				int newRed = 255-redValue;
				int newGreen = 255-greenValue;
				int newBlue = 255-blueValue;
						
				int newPixel = GImage.createRGBPixel(newRed, newGreen, newBlue);
				
				negativePixels[row][col] = newPixel;
			}
		}
		
		
		return new GImage(negativePixels);
		
	}

	public GImage greenScreen(GImage source) {
		
		int [][] pixels = source.getPixelArray();
		int [][] greenPixels = new int [pixels.length][pixels[0].length]; 
		
		for (int row =0; row<pixels.length; row++) {
			for (int col=0; col<pixels[row].length; col++) {				                         


				int pixel = pixels[row][col];
				int newPixel;
				
				int redValue = GImage.getRed(pixel);
				int greenValue = GImage.getGreen(pixel);
				int blueValue = GImage.getBlue(pixel);
				
				int redblue = Math.max(redValue, blueValue);
				
				if (greenValue >= 2*redblue) {
					newPixel = GImage.createRGBPixel(redValue, 0, blueValue,0);
				} else {
					newPixel = GImage.createRGBPixel(redValue, greenValue, blueValue);
				}
					
				greenPixels[row][col] = newPixel;
			}
		}
		
		
		return new GImage(greenPixels);
	}

	public GImage blur(GImage source) {
		
		int [][] pixels = source.getPixelArray();
		int [][] blurPixels = new int [pixels.length][pixels[0].length]; 
		
		for (int row =0; row<pixels.length; row++) {
			for (int col=0; col<pixels[row].length; col++) {				                         


				int r1 = row-1 < 0 ? 0 : row-1;
				int c1 = col-1 < 0 ? 0 : col-1;
				int r2 = row+1 > pixels.length-1 ? pixels.length-1 : row+1;
				int c2 = col+1 > pixels[0].length-1 ? pixels[0].length-1 : col+1;
				
				int[] newPixelArr = computeAverage(r1 , c1, r2, c2 , pixels);
				
				int newPixel = GImage.createRGBPixel(newPixelArr[0], newPixelArr[1], newPixelArr[2]);
					
				blurPixels[row][col] = newPixel;
			}
		}
		
		
		
		return new GImage(blurPixels);
	}
	
	public int[] computeAverage(int r1 , int c1, int r2, int c2 , int[][] arr) {
		
		int red = 0;
		int blue = 0;
		int green = 0;
		int [] output = new int[3];
		int count = 0;
		
		for (int row =r1; row<= r2; row++) {
			for (int col=c1; col<=c2; col++) {
				
				int pixel = arr[row][col];
				
				int redValue = GImage.getRed(pixel);
				int greenValue = GImage.getGreen(pixel);
				int blueValue = GImage.getBlue(pixel);
					
				red+=GImage.getRed(pixel);
				green+=GImage.getGreen(pixel);
				blue+=GImage.getBlue(pixel);
				count++;
				
			}
		}
		
		output[0] = red/count;
		output[1] = green/count;
		output[2] = blue/count;
		
		return output;
	}

	public GImage crop(GImage source, int cropX, int cropY, int cropWidth, int cropHeight) {
		
		int [][] pixels = source.getPixelArray();
		int [][] cropPixels = new int [pixels.length][pixels[0].length]; 
		
		for (int row=cropX; row<cropHeight;row++) {
			for (int col=cropY; col<cropWidth; col++) {
				cropPixels[row][col] = pixels[row][col];	
			}
		}
		
		return new GImage(cropPixels);
		
	}

	public GImage equalize(GImage source) {
		// TODO
		int [][] pixels = source.getPixelArray();
		int [][] luminosityPixels = new int [pixels.length][pixels[0].length]; 
		int [] luminosityHist = initializeHist();
		int totalPixels = pixels.length * pixels[0].length;
		
		computeLuminosityHistForImage(luminosityHist,pixels);
		

	
		
		int [] cumulativeLuminosity = computeCumulativeLuminosityHist(luminosityHist);
	
		int [] pixelsLuminosity = computePixelsLuminosity(cumulativeLuminosity,totalPixels);
		
		luminosityPixels = modifyEachPixel(pixels,pixelsLuminosity);
		
		
		
		return new GImage(luminosityPixels);
		
	}
	
	private void computeLuminosityHistForImage(int[] luminosityArr, int[][] pixels) {
		
		for (int row =0; row<pixels.length; row++) {
			for (int col=0; col<pixels[row].length; col++) {	
				
				int pixel = pixels[row][col];
				int r = GImage.getRed(pixel);
				int g = GImage.getGreen(pixel);
				int b = GImage.getBlue(pixel);
				
				int luminosity = computeLuminosity(r,g,b);
				luminosityArr[luminosity]++;	
			}
		}
	}
	
	private int [] computePixelsLuminosity(int[] cLuminosity, int totalPixels) {
		
		int modifiedLuminosity[] = new int[cLuminosity.length];
		
		for (int i =0; i<cLuminosity.length; i++) {
				double luminosity = (double) cLuminosity[i];
				double percentage = luminosity / totalPixels;
				
				double result = percentage*255;
				int rounddown = (int) Math.floor(result);
				
				modifiedLuminosity[i] = rounddown;
		}
		
		return modifiedLuminosity;
	}
	
	private int[][] modifyEachPixel(int[][] pixels, int [] pixelsLuminosity) {
		
		int [][] pixelsModified = new int [pixels.length][pixels[0].length];
		
		
		
		for (int row =0; row<pixels.length; row++) {
			for (int col=0; col<pixels[row].length; col++) {
				
				int pixel = pixels[row][col];
				int r = GImage.getRed(pixel);
				int g = GImage.getGreen(pixel);
				int b = GImage.getBlue(pixel);
				
				int luminosity = computeLuminosity(r,g,b);
				
				int newRGB = pixelsLuminosity[luminosity];
				
				int newPixel = GImage.createRGBPixel(newRGB, newRGB, newRGB);
				
				pixelsModified[row][col] = newPixel;
			}
		}
		
		return pixelsModified;
	}
	

	
	private int[] computeCumulativeLuminosityHist(int[] luminosityHist) {
		int []cumulativeLuminosityHist = initializeHist();
		int total=0;
		
		for (int i =0; i<luminosityHist.length; i++) {
			total+=luminosityHist[i];
			cumulativeLuminosityHist[i] = total;
		}
		
		return cumulativeLuminosityHist;
	}
	
	
	
	private int[] initializeHist() {
		int [] arr = new int[256];
		
		for (int i =0; i<256; i++) {
			arr[i] = 0;
		}
		
		return arr;
	}
	
	
}
