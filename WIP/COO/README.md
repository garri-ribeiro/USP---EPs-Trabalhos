>Documento no gdocs
		https://docs.google.com/document/d/14kqHSzuFWwbGfs_idAmWI6F1_e-KaZb53yQ2cPzEXVU/edit?pref=2&pli=1

>Instruções do professor 		
	http://pastebin.com/bKdimTeH

>Adendo do professor
	http://pastebin.com/iEdWV2gj 
	ou http://www.tidia-ae.usp.br/access/content/attachment/1b74c246-8c0e-4be3-be74-fa12a47ad81e/Avisos/75d2deeb-b1c9-49f7-8e14-e66d28fa4487/projeto_COO_funcionalidades_extras.pdf
	
>Modificações
	
	Bielzyn, 24/06
		(re)criou Background
		Criou BackgroundElement
		Arrumou o desenho do background no main
	
	Larissa, 23/06 
		Fez a classe interface Character, encapsulada, que serve de forma para qualquer personagem;
		Criou a classe Enemy1, que é o inimigo redondo, e Enemy2, que é o inimigo quadrado;	
		Criou a classe Projectile
		Criou a classe Background
		
>Próximos passos 
		criar classe interface inimigo base
		criar classe projétil 
		criar classe background2

		Padrões de projeto
			Criacional
				singleton (player), factory (inimigos, tiros)
			Estrutural
				facade
			Comportamental
				iterator pra percorrer tiros e inimigos (colisões)
				strategy pra mudar o comportamento do player
				state pra substituir as variaveis de estado		
		
		
>Objetivo
		deixar apenas alguns metodos disponiveis pro cliente (pelo facade)