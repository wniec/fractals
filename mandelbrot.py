import numpy as np
import matplotlib.pyplot as plt
def r2 (a,b):
    return a**2+b**2
def stays(a,b):
    p1,p2=0,0
    for i in range(48):
        p1,p2=p1**2-p2**2+a,2*p1*p2+b
        if r2(p1,p2)>=4:
            return False
    return r2(p1,p2)<4
size=4096
data=np.zeros((size,size,3),dtype=np.uint8)
for i in range(size):
    for j in range(size):
        a,b=3*i/size-2.3,2.5*j/size-1.25
        if stays(a,b):
            data[i][j]=[0,0,0]
        else:
            data[i][j]=[0,0,255-255*r2(2*i/size-1,2*j/size-1)/3]
plt.imshow(data,interpolation='nearest')
plt.show()
