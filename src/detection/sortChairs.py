
def sortChairs(chairCoordinates):
    chairCoordinates = sorted(chairCoordinates, key=lambda chair: chair[1] + chair[3])
    
    chairCoordinates[0:4] = sorted(chairCoordinates[0:4], key=lambda chair: chair[0])
    chairCoordinates[4:8] = sorted(chairCoordinates[4:8], key=lambda chair: chair[0])
    chairCoordinates[8:12] = sorted(chairCoordinates[8:12], key=lambda chair: chair[0])
    chairCoordinates[12:16] = sorted(chairCoordinates[12:16], key=lambda chair: chair[0])
    
    
    return chairCoordinates