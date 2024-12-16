INSERT INTO ROADS (id_road, name, description, type) VALUES (1000,  'Gravel road M50', 'Simple road', '1');
INSERT INTO ROADS (id_road, name, description, type) VALUES (2000,  'Asphalt road M10', 'Simple road', '2');
INSERT INTO ROADS (id_road, name, description, type) VALUES (3000,  'Concrete road M40', 'Simple road', '3');
INSERT INTO ROADS (id_road, name, description, type) VALUES (4000,  'Gravel road M340', 'Simple road', '1');


INSERT INTO SIGNS (id_sign, type, name, description, id_road, side,point) VALUES (1000,  '3', 'Caution: falling stones', 'Simple sign', 1000, 'RIGHT',1245);
INSERT INTO SIGNS (id_sign, type, name, description, id_road, side,point) VALUES (2000,  '3', 'NH', 'Simple sign', 1000, 'RIGHT',324);
INSERT INTO SIGNS (id_sign, type, name, description, id_road, side,point) VALUES (3000,  '2', 'No Parking', 'Simple sign', null, 'LEFT',5640);

INSERT INTO INTERSECTIONS (id_intersection, name) VALUES (3000,  'Simple intersection');