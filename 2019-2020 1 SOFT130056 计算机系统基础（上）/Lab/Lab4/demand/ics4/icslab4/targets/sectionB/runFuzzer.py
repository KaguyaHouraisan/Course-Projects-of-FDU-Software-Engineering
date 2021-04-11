# coding=utf-8
import os
import shutil
import random
import logging
import sys
import time
import argparse
from datetime import datetime
from multiprocessing import Process
from multiprocessing import Pool
import subprocess
import sys
import threading
from random import randint, sample

alphabetTable = range(0,128) #set("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")
def generate(minlen, maxlen):
    end = randint(minlen, maxlen)
    data = []
    for i in range(minlen, end):
        data.append(chr(sample(alphabetTable, 1)[0]))
    return ''.join(data)


def writeFile(filePath,myStr):
    dirName = os.path.dirname(filePath)
    folder = os.path.exists(dirName)
    if not folder:
        os.makedirs(dirName)
    with open(filePath,'wb+') as f:
        f.write(myStr)
    return

def generate_random_byte():
    #generate a sorted list:[0,1,...,127]
    alphabetList = range(0, 128)
    # randomly select a element and transfer to char
    new_char = chr( random.sample(alphabetList, 1)[0] )
    return new_char

def strat_random_write_byte( myInput ):
    import random
    # we got a random char here
    random_char = generate_random_byte()
    # string to list: 'abc' -> ['a','b','c']
    str_list = list( myInput )
    # gen ramdom position
    random_position = random.randint(0, len(myInput)-1)
    # overwrite the char in this position
    str_list[ random_position ] = random_char
    # list to string: ['a','b','x'] -> 'abx'
    return ''.join(str_list)    

def strat_ramdom_bit_flip( oldChar ):
    import random
    # char to int
    oldChar_int = ord(oldChar)
    # get the ramdom bit index
    random_position = random.randint(1, 7)
    # just flip
    newChar_int = oldChar_int ^ (1 << random_position)
    # int to char
    return chr(newChar_int)

def run_target(binPath, myArgs, inputs):
    import subprocess
    # run target binary with args and inputs
    handle = subprocess.run(args=[binPath,myArgs],
                            input=inputs,
                            stdout=subprocess.PIPE, 
                            stderr=subprocess.PIPE)
    retout = handle.stdout
    # error info
    reterr = handle.stderr
    # ret code
    retval = handle.returncode
    return (retout, reterr, retval)



from datetime import datetime,timedelta



if __name__ == "__main__":
    today = datetime.today()
    currTime = today.strftime('%Y-%m-%d-%H-%M-%S')
    resPath = './result-{}.txt'.format(currTime)
    writeFile(resPath,'start time:{}\n\n\n'.format(currTime).encode("utf-8"))
    while(True):
        #your strategies
        run_target('./ls','dskjfklf/dfjkajf','')
    
