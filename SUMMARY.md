User:
-> nome
-> email
-> password
-> contacto



(os Administradores criam estas infos)
Equipa:
-> Nome
-> Imagem

Jogador:
-> nome
-> posicao (defesa,lateral,medio,etc)
-> data de nascimento
-> Equipa associada

Jogo de Futebol:
-> Equipa A (casa)
-> Equipa B (fora)      -------- selecionadas a partir da lista de equipas
-> Localizacao
-> Data
-> lista de eventos (resultado do jogo e calculado analisando esta lista)
-> Administrador valida ou rejeita esses eventos


Geral:

Useres Registados:

-> Registo de evento num jogo
    (entra na plataforma e aparece lista de jogos a decorrer e jogos futuros. Ao entrar num jogo o User pode escoler os seguintes eventos)
    
    - Inicio/ Fim do Jogo
    - Golo (Seleccionar um jogador e reportar um golo desse jogador, e respectiva
            equipa, com uma data e hora associada)

    - Cartão Amarelo (Seleccionar um jogador e reportar um cartão amarelo desse
                      mesmo jogador, com uma data e hora associada)


    - Cartão Vermelho (Seleccionar um jogador e reportar um cartão amarelo desse
                      mesmo jogador, com uma data e hora associada)

    - Jogo Interrompido (Indicar que o jogo se encontra interrompido)
    - Jogo resumido (Indicar que o jogo volta a estar a decorrer após ter estado interrompido)


Users Não registados:

-> Ver detalhes de um jogo a decorrer:

    -> Nome do jogo (Equipa A vs Equipa B)
    -> Resultado actual
    -> Localização
    -> Data
    -> Listagem de eventos, ordenados de forma cronológica

-> Consultar Estatisticas

    -> Tabela de Score (nº de jogos | vitorias | derrotas | empates)
    -> Melhor Marcador de Golos (Nome, numero medio de golos por jogo, numero max de golos por jogo)
    -> Formulario (Meter duas equipas e mostrar quantoas vezes ja se defrontaram,empates,golos,total cartoes amarelos e vermelhos)