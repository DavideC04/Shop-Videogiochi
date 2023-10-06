ALTER TABLE games MODIFY COLUMN description TEXT;

INSERT INTO games (photo, title, editor, genre, description, price) VALUES ('https://gaming-cdn.com/images/products/9187/616x353/assassin-s-creed-mirage-pc-gioco-ubisoft-connect-europe-cover.jpg?v=1696491227', 'Assassin’s Creed Mirage', 'Ubisoft',' Videogioco stealth, Avventura dinamica, Picchiaduro', 'Assassins Creed Mirage per PC è un videogioco di azione e avventura, il tredicesimo capitolo principale della serie e il successore di AC Valhalla. Mirage è ambientato principalmente a Baghdad nel IX secolo e vede come protagonista Basim Ibn Ishaq. Storicamente, questo periodo corrisponde all anarchia di Samarra, un periodo instabile segnato da leadership in continua evoluzione.', 59.99);

INSERT INTO games (photo, title, editor, genre, description,  price) VALUES ('https://gaming-cdn.com/images/products/13588/616x353/ea-sports-fc-24-pc-gioco-ea-app-cover.jpg?v=1695973037', 'FC 24', 'Electronic Arts','Sport', 'EA Sports FC 24 per PC è un gioco di simulazione calcistica; il successore dei giochi FIFA, ora che la partnership con la federazione internazionale di calcio è giunta al termine. È il primo della serie, ma spiritualmente è il 31° gioco del franchise. Nonostante il cambio di nome, l azienda ha confermato in un comunicato che il nuovo franchise conserva le licenze di oltre 19.000 giocatori, 700 squadre, più di 100 stadi e oltre 30 leghe, tutte basate su persone e squadre reali.', 49.99);

INSERT INTO games (photo, title, editor, genre, description, price) VALUES ('https://gaming-cdn.com/images/products/14405/616x353/forza-motorsport-premium-edition-accesso-anticipato-pc-xbox-series-x-s-premium-edition-xbox-series-x-s-pc-gioco-microsoft-store-cover.jpg?v=1696491611', 'Forza Motorsport Premium Edition', 'Xbox Game Studios','Racing', 'Guida più di 500 auto del mondo reale, incluse auto da corsa moderne e oltre 100 vetture mai viste prima in Forza Motorsport. Ogni giro conta mentre gareggi in 20 dei circuiti più amati dai fan con molti tracciati da padroneggiare, ciascuno caratterizzato da punteggio in pista in tempo reale e ora del giorno dinamica con meteo e condizioni di guida uniche, il tutto per garantirti un feeling diverso ad ogni giro.',69.99);

INSERT INTO games (photo, title, editor, genre, description, price) VALUES ('https://gaming-cdn.com/images/products/6772/616x353/resident-evil-4-pc-gioco-steam-europe-cover.jpg?v=1684153325', 'Resident Evil', 'CAPCOM Co.','Horror,Avventura,Sparatutto', 'Sono passati sei anni dalla catastrofe biologica di Raccoon City. L agente Leon S. Kennedy, uno dei sopravvissuti all incidente, è stato inviato a salvare la figlia rapita del presidente. La individua in un villaggio europeo sperduto, dove gli abitanti hanno decisamente qualcosa che non va, e il sipario si alza su una storia di salvataggi spericolati e orrori indicibili, in un intreccio di vita e morte, terrore e catarsi.', 54.99);

INSERT INTO games (photo, title, editor, genre, description, price) VALUES ('https://gaming-cdn.com/images/products/9143/616x353/star-wars-jedi-survivor-pc-gioco-origin-cover.jpg?v=1683557097', 'Star Wars Jedi: Survivor', 'Electronic Arts', 'Avventura,Picchiaduro','La storia di Cal Kestis continua in Star Wars Jedi: Survivor, un gioco d azione e d avventura in terza persona sviluppato da Respawn Entertainment in collaborazione con Lucasfilm Games. Questo titolo per giocatore singolo e dalla forte componente narrativa riparte 5 anni dopo gli eventi di Star Wars Jedi: Fallen Order e segue gli scontri sempre più disperati di Cal, mentre la galassia scende ulteriormente nell oscurità.', 69.99);

INSERT INTO games (photo, title, editor, genre, description, price) VALUES ('https://gaming-cdn.com/images/products/9665/616x353/ratchet-clank-rift-apart-pc-gioco-steam-europe-cover.jpg?v=1690357205', 'Ratchet & Clank Rift Apart', 'Insomniac Games','Avventura', 'Fate un salto dimensionale con Ratchet e Clank, mentre si avventurano per la prima volta su PC. Aiutateli a sconfiggere un imperatore malvagio proveniente da un altra dimensione, mentre saltate ad alta velocità tra mondi ricchi di azione!', 49.99);

INSERT INTO games (photo, title, editor, genre, description, price) VALUES ('https://gaming-cdn.com/images/products/13660/616x353/f1-23-pc-gioco-ea-app-cover.jpg?v=1686902107', 'F1 23', 'Electronic Arts','Racing', 'Tieni il piede lontano dal freno in EA SPORTS™ F1® 23, il videogioco ufficiale del 2023 FIA Formula One World Championship™. Questo gioco include la possibilità di acquistare, all’interno del gioco, tramite denaro reale una valuta virtuale da utilizzare per acquistare oggetti di gioco virtuali.', 59.99);

INSERT INTO games (photo, title, editor, genre, description, price) VALUES ('https://gaming-cdn.com/images/products/14362/616x353/starfield-digital-premium-edition-xbox-series-x-s-digital-premium-edition-xbox-series-x-s-gioco-microsoft-store-cover.jpg?v=1694010752', 'Starfield Digital Premium Edition', 'Bethesda Softworks','Avventura,Sparatutto,Open World', 'Starfield è il primo nuovo universo in oltre 25 anni da Bethesda Game Studios, i pluripremiati creatori di The Elder Scrolls V: Skyrim e Fallout 4. In questo gioco di ruolo di nuova generazione ambientato tra le stelle, crea il personaggio che desideri ed esplora con una libertà senza precedenti, in un epico viaggio per scoprire la risposta al più grande mistero dell umanità.', 69.99);

INSERT INTO consoles (console) VALUES ("PlayStation 5");
INSERT INTO consoles (console) VALUES ("PlayStation 4");
INSERT INTO consoles (console) VALUES ("Nintendo Switch");
INSERT INTO consoles (console) VALUES ("Xbox");

INSERT INTO games_console_list (videogame_id, console_list_id) VALUES(1,1),(1,2),(1,4);

INSERT INTO games_console_list (videogame_id, console_list_id) VALUES(2,1),(2,2),(2,3),(2,4);

INSERT INTO games_console_list (videogame_id, console_list_id) VALUES(3,4);

INSERT INTO games_console_list (videogame_id, console_list_id) VALUES(4,1),(4,2),(4,4);

INSERT INTO games_console_list (videogame_id, console_list_id) VALUES(5,1),(5,2),(5,4);

INSERT INTO games_console_list (videogame_id, console_list_id) VALUES(6,2);

INSERT INTO games_console_list (videogame_id, console_list_id) VALUES(7,1),(7,2),(7,4);

INSERT INTO games_console_list (videogame_id, console_list_id) VALUES(8,4);
