# Funcionalidade a concretizar
A funcionalidade necessária para a entrega intermédia, além da abertura de todos os submenus, é a seguinte:

- Leitura, interpretação e representação em memória do ficheiro textual indicado pela propriedade import (implica implementar algumas classes do "core")
- Menu principal: Salvaguarda do estado actual da aplicação: "Abrir" e "Guardar" -- implementação completa
- Menu principal: Gestão e consulta de dados da aplicação: abertura dos vários submenus (comandos fornecidos já implementados).
- Menu de Gestão de Terminais -- implementar as operações "Mostrar todos os terminais", "Registar terminal" e "Menu da consola de um terminal".
- Menu de gestão de clientes -- implementar as operações "Visualizar cliente", "Visualizar todos os clientes" e "Registar cliente".
- Menu de Consultas -- implementar a operação "Mostrar terminais sem actividade".

Todos os outros comandos em todos os menus têm de estar concretizados ("execute" pode estar vazio). Não é má prática implementar os outros comandos, pois poupa esforço para a entrega final, mas não serão avaliados nesta entrega.

Note-se que os comandos que abrem os vários menus da aplicação já estão concretizados e portanto não podem ser alterados. Note-se ainda que a classe **App** e os vários menus já disponibilizados no esqueleto da aplicação também não podem ser alterados.

A concretização das entidades do domínio da aplicação (pertencentes a **prr.core**) tem de ser pelo menos o suficiente para suportar os comandos necessários para concretizar a funcionaldiade indicada acima. Não é obrigatório fazer a concretização total do domínio da aplicação. Por exemplo, um terminal não precisa ainda de suportar a funcionalidade de estabelecer uma comunicação.