.data 
array1: .word 2,3,4,6,7,8,9,12,15 #armazenando os dois vetores para lidar com o código sem importar arquivos
array2: .word 1,3,5,6,7,8 #após fazer o merge funcionar, tentar utilizar a leitura de arquivos somente para substituir esses vetores
array3: .byte 1000 #vetorzão número três esperança da nação

.text
la $s0,array1
la $s1,array2
la $s2,array3
li $s3,9 #Fazer função bonitinha pra percorrer arrays no futuro
li $s4,6 #tam2

WHILE: #salvar o endereço do fim de array de alguma forma para fins de comparação oooooooooou sei lá
la $t3,array2
la $t4,array3
bge $s0,$t3, SOBROU2
bge $s1,$t4, SOBROU1
lw $t1,($s0)
lw $t2,($s1)
blt $t1,$t2,MENOR
bgt $t1,$t2,MAIOR
beq $t1,$t2,IGUAL

MENOR:
sw $t1,4($s2)
addi $s0,$s0,4
addi $s2,$s2,4
j WHILE

MAIOR:
sw $t2,4($s2)
addi $s1,$s1,4
addi $s2,$s2,4
j WHILE

IGUAL:
sw $t1,4($s2)
addi $s0,$s0,4
addi $s1,$s1,4
addi $s2,$s2,4
j WHILE

SOBROU1:
bge $s0,$t3, FIM
lw $t1,($s0)
sw $t1,4($s2)
addi $s0,$s0,4
addi $s2,$s2,4
j SOBROU1

SOBROU2:
bge $s1,$t4, FIM
lw $t2,($s1)
sw $t2,4($s2)
addi $s1,$s1,4
addi $s2,$s2,4
j SOBROU2

FIM: