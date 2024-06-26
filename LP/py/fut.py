def soccerManager(matches, placement, currentMatch = 0):
    if len(matches) == 0:
        return placement
    if currentMatch >= len(matches):
        return placement
    else:
        if matches[currentMatch][0] > matches[currentMatch][1]:
            placement = (placement[0] + 1, placement[1], placement[2])
            return soccerManager(matches, placement, currentMatch+1)
        elif matches[currentMatch][0] < matches[currentMatch][1]:
            placement = (placement[0], placement[1] + 1, placement[2])
            return soccerManager(matches, placement, currentMatch+1)
        elif matches[currentMatch][0] == matches[currentMatch][1]:
            placement = (placement[0], placement[1], placement[2] + 1)
            return soccerManager(matches, placement, currentMatch+1)
        else:
            return soccerManager(matches, placement, currentMatch+1)


matches = [(0,1), (1,0), (1,1), (2,2), (0,2), (2,0)]
matches7_1 = [(7,1), (7,1), (7,1), (7,1), (7,1), (7,1), (7,1), (0,1)]

print(soccerManager(matches7_1, (0,0,0)))
