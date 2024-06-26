def alunoManager(alunos, aprovacao, alunoAtual=0):
    def bombaPorFalta(x, y):
        if (int((x / 4) * 3) < y): 
            return True
        else: 
            return False
    
    def bombaPorNota(x):
        if (x < 60):
            return True
        else: 
            return False
        
    if alunoAtual >= len(alunos):
        qtdAlunos = len(alunos)
        aprovacao = [((aprovacao[0] / qtdAlunos) * 100), ((aprovacao[1] / qtdAlunos) * 100), ((aprovacao[2] / qtdAlunos) * 100)]
        return aprovacao
    
    bombouPorFalta = bombaPorFalta(alunos[alunoAtual][0], alunos[alunoAtual][1])
    bombouPorNota = bombaPorNota(alunos[alunoAtual][2])
    
    if bombouPorFalta:
        aprovacao = (aprovacao[0], aprovacao[1] + 1, aprovacao[2])
    
    if bombouPorNota:
        aprovacao = (aprovacao[0], aprovacao[1], aprovacao[2] + 1)
    
    if not (bombouPorFalta or bombouPorNota):
        aprovacao = (aprovacao[0] + 1, aprovacao[1], aprovacao[2])
    
    return alunoManager(alunos, aprovacao, alunoAtual + 1)

aluno = [(68, 2, 78), (70, 0, 89), (68, 16, 48), (72, 4, 80)]
qtdAlunos = len(aluno)
aprovacoes = alunoManager(aluno, (0, 0, 0))

print(aprovacoes)




