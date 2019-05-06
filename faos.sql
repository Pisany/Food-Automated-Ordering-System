
CREATE TABLE kategoria_produktu (
    id_kat_prod   INTEGER NOT NULL,
    nazwa         VARCHAR(64) NOT NULL
);

ALTER TABLE kategoria_produktu ADD CONSTRAINT kategoria_produktu_pk PRIMARY KEY ( id_kat_prod );

CREATE TABLE platnosc (
    id_platnosci       INTEGER NOT NULL,
    nazwa              VARCHAR(32) NOT NULL,
    rodzaj_platnosci   INTEGER NOT NULL
);

ALTER TABLE platnosc ADD CONSTRAINT platnosc_pk PRIMARY KEY ( id_platnosci );

CREATE TABLE produkt (
    id_produktu   INTEGER NOT NULL,
    nazwa         VARCHAR(64) NOT NULL,
    cena_szt      INTEGER NOT NULL,
    waga          VARCHAR(8),
    id_kat_prod   INTEGER NOT NULL,
	file_path	  VARCHAR(64) 
);

ALTER TABLE produkt ADD CONSTRAINT produkt_pk PRIMARY KEY ( id_produktu );

CREATE TABLE urzadzenie (
    id_urzadzenia   INTEGER NOT NULL,
    nazwa           VARCHAR(64) NOT NULL
);

ALTER TABLE urzadzenie ADD CONSTRAINT urzadzenie_pk PRIMARY KEY ( id_urzadzenia );

CREATE TABLE zamowienia_szczegoly (
    ilosc            INTEGER NOT NULL,
    koszt            INTEGER NOT NULL,
    id_produktu      INTEGER NOT NULL,
    id_zammowienia   INTEGER NOT NULL
);

ALTER TABLE zamowienia_szczegoly ADD CONSTRAINT zamowienia_szczegoly_pk PRIMARY KEY ( id_produktu,
                                                                                      id_zammowienia );

CREATE TABLE zamowienie (
    id_zammowienia   INTEGER NOT NULL,
    data             DATE NOT NULL,
    id_urzadzenia    INTEGER NOT NULL,
    id_platnosci     INTEGER NOT NULL
);

ALTER TABLE zamowienie ADD CONSTRAINT zamowienie_pk PRIMARY KEY ( id_zammowienia );

ALTER TABLE zamowienie
    ADD CONSTRAINT zamowienie_platnosc_fk FOREIGN KEY ( id_platnosci )
        REFERENCES platnosc ( id_platnosci );

ALTER TABLE zamowienie
    ADD CONSTRAINT zamowienie_urzadzenie_fk FOREIGN KEY ( id_urzadzenia )
        REFERENCES urzadzenie ( id_urzadzenia );

INSERT INTO `kategoria_produktu` (`id_kat_prod`, `nazwa`) VALUES ('1', 'first_page'), ('2', 'second_page'), ('3', 'third_page'), ('4', 'fourth_page');



CREATE TRIGGER check_zamowienia BEFORE INSERT ON zamowienia_szczegoly
    FOR EACH ROW
	BEGIN
		IF NEW.ilosc<1 THEN
			ROLLBACK;
		END IF;
    END
	
CREATE TRIGGER koszt_zamowienia BEFORE INSERT ON zamowienia_szczegoly
    FOR EACH ROW
	BEGIN
    	DECLARE cena INTEGER
        SET cena = (SELECT produkt.cena_szt FROM produkt LEFT JOIN zamowienia_szczegoly ON produkt.id_produktu = zamowienia_szczegoly.id_produktu WHERE produkt.id_produktu = zamowienia_szczegoly.id_produktu )
    	INSERT INTO zamowienia_szczegoly SET NEW.koszt = NEW.ilosc*cena;
    END;
	
		CREATE VIEW rachunek_id1 AS SELECT zs.id_zammowienia, pr.nazwa, pr.waga, pr.cena_szt, zs.ilosc, zs.koszt FROM zamowienia_szczegoly zs LEFT JOIN produkt pr ON zs.id_produktu = pr.id_produktu where zs.id_zammowienia = '1';
		CREATE VIEW rachunek_id2 AS SELECT zs.id_zammowienia, pr.nazwa, pr.waga, pr.cena_szt, zs.ilosc, zs.koszt FROM zamowienia_szczegoly zs LEFT JOIN produkt pr ON zs.id_produktu = pr.id_produktu where zs.id_zammowienia = '2';
		CREATE VIEW rachunek_id3 AS SELECT zs.id_zammowienia, pr.nazwa, pr.waga, pr.cena_szt, zs.ilosc, zs.koszt FROM zamowienia_szczegoly zs LEFT JOIN produkt pr ON zs.id_produktu = pr.id_produktu  where zs.id_zammowienia = '3';
		CREATE VIEW rachunek_all AS SELECT zs.id_zammowienia, pr.nazwa, pr.waga, pr.cena_szt, zs.ilosc, zs.koszt FROM zamowienia_szczegoly zs LEFT JOIN produkt pr ON zs.id_produktu = pr.id_produktu;

INSERT INTO `produkt` (`id_produktu`, `nazwa`, `cena_szt`, `waga`, `id_kat_prod`, `file_path`) VALUES ('1', 'Mega Burger', '4', '250', '1', 'Icon/Meal/Burgers/mega-burger.png'), ('2', 'Classic Burger', '3', '150', '1', 'Icon/Meal/Burgers/classic-burger.png'), ('3', 'Vege Burger', '3', '200', '1', 'Icon/Meal/Burgers/vege-burger.png'), ('4', 'Sausage Burger', '5', '150', '1', 'Icon/Meal/Burgers/sausage-burger.png'), ('5', 'Pizza', '6', '180', '1', 'Icon/Meal/Burgers/pizza.png'),('6', 'Friend Dumplings', '7', '350', '1', 'Icon/Meal/Burgers/fried-dumplings.png'), ('7', 'Small Fries', '3', '100', '2', 'Icon/Meal/Fries/small-fries.png'), ('8', 'Big Fries', '6', '250', '2', 'Icon/Meal/Fries/big-fries.png'), ('9', 'French Fries', '6', '200', '2', 'Icon/Meal/Fries/french-fries.png'), ('10', 'Spin Fries', '4', '150', '2', 'Icon/Meal/Fries/spin-fries.png'), ('11', 'Bottle Water', '2', '150', '3', 'Icon/Meal/Drinks/bottle-water.png'), ('12', 'Green Juice', '3', '150', '3', 'Icon/Meal/Drinks/bottle-green-juice.png'), ('13', 'Bottle Red Juice', '4', '150', '3', 'Icon/Meal/Drinks/bottle-red-juice.png'), ('14', 'Ice Orange Lemon ', '3', '200', '3', 'Icon/Meal/Drinks/ice-orange-lemon.png'), ('15', 'Ice Red Lemon', '4', '200', '3', 'Icon/Meal/Drinks/ice-red-lemon.png'), ('16', 'Apple Juice', '4', '150', '3', 'Icon/Meal/Drinks/apple-juice.png'), ('17', 'Chocolate cookie', '2', '80', '4', 'Icon/Meal/Cake/chocolate-cookie.png'), ('18', 'Berries Cupacke', '3', '90', '4', 'Icon/Meal/Cake/berries-cupcake.png'), ('19', 'Cream cupacke', '3', '110', '4', 'Icon/Meal/Cake/cream-cupcake.png'), ('20', 'Ice Cake', '3', '80', '4', 'Icon/Meal/Cake/ice-cake.png'), ('21', 'Ice Cream Sandwich', '4', '130', '4', 'Icon/Meal/Cake/ice-cream-sandwich.png')