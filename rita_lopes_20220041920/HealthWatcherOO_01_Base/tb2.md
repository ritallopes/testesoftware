Campo | Classes Válidas | Classes Inválidas
Mês | c1: 1 <= mes <= 12 | i1: mes < 1 
    |                  | i2: mes > 12
Dia | c2: mes E {4, 6, 9, 11} ? 1 <= dia <=  30 | i3: mes Ẽ {1, 3, 5, 7, 8, 10, 12} e dia > 30
    | c3: mes E {1, 3, 5, 7, 8, 10, 12} ? 1 <= dia <= 31 | i4: mes Ẽ {4, 6, 9, 11} e dia > 31
    | c4: mes E {2} e ano não bissexto ? 1 <= dia <= 28 | i5: mes E {2} e ano não bissexto e dia > 28 
    | c5: mes E {2} e ano bissexto ? 1 <= dia <= 29 | i6: mes E {2} e ano não bissexto e dia > 29 
    |                                               |  i7: dia < 1
Ano | c6: 1582 <= ano <= 3000 | i8: ano < 1582
    |                         | i9: ano > 3000
Segundo | c7: 0 <= segundo < 60 | i10: segundo < 0
        |                       | i11: segundo >=60
Minuto | c8: 0 <= minuto < 60 | i12: minuto < 0
        |                      | i13: minuto >= 60
Hora | c9: 0 <= hora < 24 | i14: hora < 0
    |                      | i15: hora >= 24
Formato | dd/mm/aaaa, dd/mm/aaaa:hh:mi:ss, ddmmaaaa, ddmmaaaahhmiss, dd/mm/aaaa