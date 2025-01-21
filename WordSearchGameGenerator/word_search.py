#Project 1
# CSCI 1913 Fall 2022
# Author: Syed Hashimi



# Assumptions: assume the letter grid has width W and height H
# Further assuming the word parameter has length L (for find) and than the max_len parameter is L (for extract)
# concatenating a letter to a string takes time O(1)

# Question 1: What is the worst-case big-O runtime of your get_size function?
Question1 = '''
    The worst and best run time for this function is the same. The runtime is BigO(1).
'''

# Question 2: What is the worst-case big-O runtime of your copy_word_grid function?
Question2 = '''
    The worst case runtime of this function is BigO(W*H). The function has a nested for loop the outer for loop is looping over the height and the inner is looping over the width. Thus the runtime is BigO(W*H)  
'''

# Question 3: What is the worst-case big-O runtime of your extract function?
Question3 = '''
    The worst case for this function is when the if statement is true every time. There is only one for loop so the runtime is BigO(L)
'''

# Question 4: What is the worst-case big-O runtime of your find function?
Question4 = '''
    The worst case for this function is when the if statement checking if the extracted word is the same as the target word is false every time. This function calls the extracted function which has a runtime of BigO(L) and this function has three nested for loops. The last for-loop in the worst case only runs four times and in it calls the extracted function. So the runtime of this function is BigO(W*H*L)
'''


import random

# This code defines valid directions a word can travel.
# Each direction is a tuple (dx, dy) that says how you change x and y 
# coordinates to go in a given direction.
RIGHT=(1, 0)       # to go right add 1 to x
DOWN=(0,1)         # to go down add 1 to y
RIGHT_DOWN=(1, 1)  # to go right_down add 1 to both x and y
RIGHT_UP=(1,-1)    # to go right_up add 1 to x and subtract 1 from y
DIRECTIONS = (RIGHT, DOWN, RIGHT_DOWN, RIGHT_UP)


def get_size(word_grid):
    """returns a tuple for the size of the word grid provided. The format of the tuple is (width, height)"""
    return(len(word_grid[0]), len(word_grid))

def print_word_grid(word_grid):
    """prints each letter from the word grid provided. This function does not print in list format. Prints all the letters from the first element then calls a newline, then the process is repeated until all the letters are printed."""
    for i in range(len(word_grid)):
        for j in range(len(word_grid[0])):
            print(word_grid[i][j], end= '')
        print()

def copy_word_grid(word_grid):
    """copies the contents of the word grid provided. This function does not change the word_grid itself, just makes a copy of it and return it"""
    copy = [None] * len(word_grid)
    temp = []

    for i in range(len(word_grid)):
        for j in range(len(word_grid[0])):
               temp.append(word_grid[i][j])
        copy[i] = temp
        temp = [] #resets the temp list so that the next row can be copied.

    return copy

def extract(word_grid, position, direction, max_len):
    """Given the word grid, a position for the start of the word, the direction where the rest of the letters of the word is located in the word grid, and the length of the word this function makes a string by combining each letter with the given paramters. Then the function returns the extracted word."""
    extracted_word = ""
    next = [position[0], position[1]]

    for i in range(max_len):
        if 0 <= next[0] < len(word_grid[0]) and 0 <= next[1] < len(word_grid):
            extracted_word = extracted_word + word_grid[next[1]][next[0]]
        next =  next_path(next, direction)

    return extracted_word

def find(word_grid, word):
    """With a targeted word given in the parameter the words is searched in the provided wor grid. If the word is found the function returns a tuple of the position of the firts letter of the word and the direction of where the rest of the word is headed, if the word does not exist in the word grid the function returns 'None'."""
    for i in range(len(word_grid)):
        for j in range(len(word_grid[0])):
            for dir in DIRECTIONS:
                extracted_word = extract(word_grid, (i, j), dir, len(word))
                if extracted_word == word:
                    return ((i, j), dir)
    return None
   
def show_solution(word_grid, word):
    """A copy of the word grid is crearted. Given a target word from the parameter of the function the find function is called and if the word exists then it will give a tuple the location of the first letter of the target word and the direction of the rest of the word, else it will give 'None'. If word-location holds a value of 'None' then the funciton prints saying the target word is not found. If the word exists the function changes the copy word grid such that the letters of the target words in the copy grid are all upper case and then prints the copy."""
    grid_copy = copy_word_grid(word_grid)
    word_location = find(word_grid, word)
    
    if word_location == None:
        print (word, "is not found in this word search")

    else:
        position = [word_location[0][0], word_location[0][1]]

        for i in range(len(word)):
            if 0 <= position[0] < len(word_grid[0]) and 0 <= position[1] < len(word_grid):
                grid_copy[position[1]][position[0]] = grid_copy[position[1]][position[0]].upper()
            position = next_path(position, word_location[1])

        print(word.upper(), "can be found as below")
        print_word_grid(grid_copy)

def make_empty_grid(width, height):
    """This function creates a list of lists with the given height and width parameters. The list of lists contains '?' in all indexes to represent that the index is empty. The function returns the empty_grid."""
    empty_grid = [0] * height
    temp = []

    for i in range(len(empty_grid)):
        for j in range(width):
            temp.append('?')
        empty_grid[i] = temp
        temp = []

    return empty_grid

def can_add_word(word_grid, word, position, direction):
    """This funcitons checks for availiable space in the word grid for the given target word. If space is available then the function returns True else returns False"""
    next = [position[0], position[1]]
    can_add = False

    for i in range(len(word)):
        if 0 <= next[0] < len(word_grid[0]) and 0 <= next[1] < len(word_grid):
            if word_grid[next[1]][next[0]] == '?' or word[i] == word_grid[next[1]][next[0]]:
                can_add = True
        else:
            can_add = False
        next = next_path(next, direction)

    return can_add

def do_add_word(word_grid, word, position, direction):
    """This function adds each letter of the provided word in the word grid in the position and direction which was given. The function returns the changed word_grid."""
    next = [position[0], position[1]]

    for i in range(len(word)):
        word_grid[next[1]][next[0]] = word[i]
        next =  next_path(next, direction)

    return word_grid

def fill_blanks(word_grid):
    """This function loops over the whole word grid and changes '?' for a random letter in the word grid."""
    letters = "abcdefghijklmnopqrstuvwxyz"
    (width, height) = get_size(word_grid)
    for i in range(height):
        for j in range(width):
            if word_grid[i][j] == '?':
                word_grid[i][j] = random.choice(letters)
    

def next_path(next, direction):
    """This is a helper function. Given a position and direction the function increments the next varible so that it heads towards the given direction. The function return the next position."""
    if direction[0] == 1:
            next[0]+=1
    if direction[1] == -1:
            next[1]-=1
    if direction[1] == 1:
            next[1]+=1
    return next


####
#
#  PROVIDED CODE -- You shouldn't need to change any of this.
#  (it's not that we didn't think you could write this, it's this stuff is either
#  1) really easy and not worth putting in a 1913 project or
#  2) really, really specific. (it's hard to describe the correct function of
#     these two functions without just telling you exactly how to do it.)
#
#  These are provided to "complete" the project -- I.E. these work with the code you write and allow you to use your
#  functions to generate word-searches for personal use. It is RECOMMENDED that you build a front-end for this behavior
#  so you can more easily use and play-with the finished product.
####
def add_word(word_grid, word):
    ''' Attempts to '''
    width, height = get_size(word_grid)
    for attempt_num in range(50):
        direction = random.choice(DIRECTIONS)
        x = random.randrange(width)
        y = random.randrange(height)
        location = (x, y)
        if can_add_word(word_grid, word, location, direction):
            do_add_word(word_grid, word, location, direction)
            return True
    return False

def generate(width, height, words):
    words_actual = []
    word_grid = make_empty_grid(width, height)
    for word in words:
        if add_word(word_grid, word):
            words_actual.append(word)
    fill_blanks(word_grid)
    return word_grid, words_actual
