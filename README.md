# ErgastRestAPI

WebService feito com SpringBoot com resposta em JSON.

URL: http://localhost:9090/

PARAMETROS DE ENTRADA:
-season
valores aceitos: ano com quarto digitos ou 'current'
não obrigatório(default: ano atual)

-round 
valores aceitos: 'next' ou 'last') 
não obrigatório(default: last)

PATHS:
http://localhost:9090/results
http://localhost:9090/drivers
http://localhost:9090/circuits
http://localhost:9090/constructors


EXEMPLO DE CHAMADA: http://localhost:9090/results/?season=2014&round=next
 
