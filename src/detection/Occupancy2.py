import requests, time
from pprint import pprint

def 자리에없음():
    pass

def 자리에있음(i, 현재상태):
    if 현재상태 == True:
        자리정보 = {'id' : i, 'camState' : 1, 'detectionTime' : None, 'sectionId' : section_id}
        r = requests.put(f'https://i5a209.p.ssafy.io:12346/seat/detection/', json = 자리정보).json()
        if r['message'] == 'Success':
            print(f'{i} 자리에 사람이 있네용. 상태 전송 성공')

def 자리비움():
    pass

def 퇴실할까(누적자리정보, i):
    if 누적자리정보[i][-10:-1].count(False) > 10 * 0.7:
        print(f'{i} 자리가 60회 이상 비어있어 퇴실요청을 보냅니다.')
        자리정보 = {'id' : i, 'camState' : 3, 'detectionTime' : time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time())), 'sectionId' : section_id}
        r = requests.put(f'https://i5a209.p.ssafy.io:12346/seat/detection/', json = 자리정보).json()
        print(r)
        print(f'{i} 자리를 퇴실조치하여 누적자리정보를 초기화 합니다.')
        누적자리정보[i] = []
        

def 카톡할까(누적자리정보, i):
    if 누적자리정보[i][-5:-1].count(False) > 5 * 0.7:
        print(f'{i} 자리가 30회 이상 비어있어 카카오톡을 보냅니다.')
        자리정보 = {'id' : i, 'camState' : 2, 'detectionTime' : time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time())), 'sectionId' : section_id}
        r = requests.put(f'https://i5a209.p.ssafy.io:12346/seat/detection/', json = 자리정보).json()
        print(r)

    

# def 복귀여부(개별누적자리정보):
#     if 개별누적자리정보[-5:-1].count(True) > 5 * 0.7:
#         return '돌아왔어요'
#     else:
#         return '아직도없어요'


def 프론트상태확인(i):
    r = requests.get(f'https://i5a209.p.ssafy.io:12346/seat/{i}/{section_id}').json()
    return r['frontState']
        


누적자리정보 = {}
section_id = 9999


def 자리확인(현재자료):
    for data in 현재자료:
        i = data[0]
        현재상태 = data[1]
        자리에있음(i, 현재상태)
        try:
            누적자리정보[i].append(현재상태)
        except KeyError:
            누적자리정보[i] = []
            누적자리정보[i].append(현재상태)
    for i in range(len(누적자리정보)):
        프론트상태 = 0
        프론트상태 = 프론트상태확인(i)
        if 프론트상태 == 0: # 자리가 사용중이 아님
            누적자리정보[i] = []
            print(f'{i} 자리의 frontState가 0이므로 누적자리정보를 초기화 합니다.')
        elif 프론트상태 == 1: # 자리가 사용중
            if len(누적자리정보[i]) > 5:
                카톡할까(누적자리정보, i)
        elif 프론트상태 == 2:
            if len(누적자리정보[i]) > 10:
                퇴실할까(누적자리정보, i)
                
            # 복귀여부 = 복귀여부(누적자리정보[i])
            # if 복귀여부 == '돌아왔어요':
            #     print(f'{i} 자리의 이용자가 돌아와 누적자리정보를 초기화 합니다.')
            #     누적자리정보[i] = []
            # elif 복귀여부 == '아직도없어요':
    print(누적자리정보)