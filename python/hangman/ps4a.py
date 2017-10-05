
# Problem Set 4A
# Name: <Arsen>
# Collaborators:
# Time Spent: x:xx

def get_permutations(sequence):


    if len(sequence) == 1:
        return [sequence]

    else:
        perms= get_permutations(sequence[1:])
        char = sequence[0]
        result = []
        for perm in perms:
            for i in range(len(perm) +1):
                result.append(perm[:i] + char + perm[i:])
        return result




    #delete this line and replace with your code here

if __name__ == '__main__':
#    #EXAMPLE
  example_input = 'abc'
#    print('Input:', example_input)
#    print('Expected Output:', ['abc', 'acb', 'bac', 'bca', 'cab', 'cba'])
  print('Actual Output:', get_permutations(example_input))
    
#    # Put three example test cases here (for your sanity, limit your inputs
#    to be three characters or fewer as you will have n! permutations for a 
#    sequence of length n)

  pass #delete this line and replace with your code here

