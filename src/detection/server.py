# -*- coding: utf-8 -*-

from socket import *
import os, datetime, time

while True:
    serverSock = socket(AF_INET, SOCK_STREAM)
    serverSock.bind(('', 8080))
    serverSock.listen(1)

    filename = f'Day{datetime.datetime.now().day}_{datetime.datetime.now().hour}h_{datetime.datetime.now().minute}m_{datetime.datetime.now().second}s.jpg'

    connectionSock, addr = serverSock.accept()

    data = connectionSock.recv(1024)
    data_transferred = 0

    nowdir = os.path.dirname(os.path.realpath(__file__))
    with open(nowdir+"\\"+filename, 'wb') as f: #현재dir에 filename으로 파일을 받는다
        try:
            while data: #데이터가 있을 때까지
                f.write(data) #1024바이트 쓴다
                data_transferred += len(data)
                data = connectionSock.recv(1024) #1024바이트를 받아 온다
        except Exception as ex:
            print(ex)
    print('파일 %s 받기 완료. 전송량 %d' %(filename, data_transferred))
    time.sleep(3)