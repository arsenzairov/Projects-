# Problem Set 4B
# Name: <your name here>
# Collaborators:
# Time Spent: x:xx

import string



### HELPER CODE ###
def load_words(file_name):
    '''
    file_name (string): the name of the file containing 
    the list of words to load    
    
    Returns: a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    '''
    print("Loading word list from file...")
    # inFile: file
    inFile = open(file_name, 'r')
    # wordlist: list of strings
    wordlist = []
    for line in inFile:
        wordlist.extend([word.lower() for word in line.split(' ')])
    print("  ", len(wordlist), "words loaded.")
    return wordlist

def is_word(word_list, word):

    word = word.lower()
    word = word.strip(" !@#$%^&*()-_+={}[]|\:;'<>?,./\"")


    return word in word_list



def get_story_string():
    """
    Returns: a story in encrypted text.
    """
    f = open("story.txt", "r")
    story = str(f.read())
    f.close()
    return story

### END HELPER CODE ###

WORDLIST_FILENAME = 'words.txt'
b = load_words(WORDLIST_FILENAME)

class Message(object):
    def __init__(self, text):
        self.message_text = text
        self.valid_words = is_word(b, text)








    def get_message_text(self):  # Return immutable version of the message text
        return self.message_text

    def get_valid_words(self):
        return self.valid_words



    def build_shift_dict(self, shift):

        result = ''
        for i in range(len(self.message_text)):
            result += chr( ord(self.message_text[i]) + shift)

        dictionary = {}
        for k in range(len(self.message_text)):
                dictionary[self.message_text[k]] = result[k]

        return dictionary


    def apply_shift(self, shift):
        if shift >= 0 and shift < 26:
            result = ''
            for i in range(len(self.message_text)):
                if self.message_text[i].isupper():
                    result += (chr(((ord(self.message_text[i]) - 65 + shift) % 26) + 65))
                else:
                    result += (chr(((ord(self.message_text[i]) - 97 + shift) % 26) + 97))

            return result






            '''
        Applies the Caesar Cipher to self.message_text with the input shift.
        Creates a new string that is self.message_text shifted down the
        alphabet by some number of characters determined by the input shift        
        
        shift (integer): the shift with which to encrypt the message.
        0 <= shift < 26

        Returns: the message text (string) in which every character is shifted
             down the alphabet by the input shift
        '''




class PlaintextMessage(Message):
    def __init__(self, text, shift):
        Message.__init__(self,text)
        self.shift = shift

        '''
        Initializes a PlaintextMessage object        
        
        text (string): the message's text
        shift (integer): the shift associated with this message

        A PlaintextMessage object inherits from Message and has five attributes:
            self.message_text (string, determined by input text)
            self.valid_words (list, determined using helper function load_words)
            self.shift (integer, determined by input shift)
            self.encryption_dict (dictionary, built using shift)
            self.message_text_encrypted (string, created using shift)

        '''
        pass #delete this line and replace with your code here

    def get_shift(self):
        return self.shift

    def get_encryption_dict(self):

        return self.build_shift_dict(self.get_shift())
        '''
        Used to safely access a copy self.encryption_dict outside of the class
        
        Returns: a COPY of self.encryption_dict
        '''
        pass #delete this line and replace with your code here

    def get_message_text_encrypted(self):

        return self.apply_shift(self.get_shift())



        '''
        Used to safely access self.message_text_encrypted outside of the class
        
        Returns: self.message_text_encrypted
        '''
        pass #delete this line and replace with your code here

    def change_shift(self, shift):

        self.shift = shift
        return self.apply_shift(self.shift)


        '''
        Changes self.shift of the PlaintextMessage and updates other 
        attributes determined by shift.        
        
        shift (integer): the new shift that should be associated with this message.
        0 <= shift < 26

        Returns: nothing
        '''
        pass #delete this line and replace with your code here


class CiphertextMessage(Message):
    def __init__(self, text):
        Message.__init__(self,text)
        '''
        Initializes a CiphertextMessage object
                
        text (string): the message's text

        a CiphertextMessage object has two attributes:
            self.message_text (string, determined by input text)
            self.valid_words (list, determined using helper function load_words)
        '''
        pass #delete this line and replace with your code here

    def decrypt_message(self):

        i = 0
        a = ''


        while a not in b:
            i+=1
            a = self.apply_shift(i)

        else:
            return i,a






        '''
        Decrypt self.message_text by trying every possible shift value
        and find the "best" one. We will define "best" as the shift that
        creates the maximum number of real words when we use apply_shift(shift)
        on the message text. If s is the original shift value used to encrypt
        the message, then we would expect 26 - s to be the best shift value 
        for decrypting it.

        Note: if multiple shifts are equally good such that they all create 
        the maximum number of valid words, you may choose any of those shifts 
        (and their corresponding decrypted messages) to return

        Returns: a tuple of the best shift value used to decrypt the message
        and the decrypted message text using that shift value
        '''
        pass #delete this line and replace with your code here

if __name__ == '__main__':

#    #Example test case (PlaintextMessage)
#    plaintext = PlaintextMessage('hello', 2)
#    print('Expected Output: jgnnq')
#    print('Actual Output:', plaintext.get_message_text_encrypted())
#
#    #Example test case (CiphertextMessage)
#    ciphertext = CiphertextMessage('jgnnq')
#    print('Expected Output:', (24, 'hello'))
#    print('Actual Output:', ciphertext.decrypt_message())



    #TODO: WRITE YOUR TEST CASES HERE

    #TODO: best shift value and unencrypted story


    plaintext = PlaintextMessage('slapdash', 3)
    print('Actual Output:', plaintext.get_message_text_encrypted())

    ciphertext = CiphertextMessage('vodsgdvk')
    print('Expected Output:', (3, 'slapdash'))
    print('Actual Output:', ciphertext.decrypt_message())


    pass #delete this line and replace with your code here

    # Example test case (PlaintextMessage)



 #Example test case (PlaintextMessage)




