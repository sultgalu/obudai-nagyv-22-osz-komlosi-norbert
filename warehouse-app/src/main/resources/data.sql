INSERT INTO customer (id, username, password, role) VALUES (1, 'nemenek', '1234', 'ROLE_CUSTOMER');
INSERT INTO customer (id, username, password, role) VALUES (2, 'sultgalu', '1234', 'ROLE_CUSTOMER');
INSERT INTO customer (id, username, password, role) VALUES (3, 'dzsi7', '1234', 'ROLE_CUSTOMER');
INSERT INTO customer (id, username, password, role) VALUES (4, 'admin', 'admin', 'ROLE_ADMIN');

INSERT INTO STORAGE_ROOM (id, owner_id, is_free, x, y) VALUES (1, 1, false, 100, 100);
INSERT INTO STORAGE_ROOM (id, owner_id, is_free, x, y) VALUES (2, 3, false, 300, 400);
INSERT INTO STORAGE_ROOM (id, owner_id, is_free, x, y) VALUES (3, 1, false, 300, 500);
INSERT INTO STORAGE_ROOM (id, owner_id, is_free, x, y) VALUES (4, 2, false, 640, 600);
INSERT INTO STORAGE_ROOM (id, owner_id, is_free, x, y) VALUES (5, 3, false, 740, 700);

INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (1, 1, 1, 2, 2);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (2, 1, 1, 4, 6);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (3, 3, 2, 2, 7);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (4, 3, 2, 1, 23);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (5, 1, 3, 23, 3);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (6, 1, 3, 123, 34);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (7, 2, 4, 23, 33);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (8, 2, 4, 66, 43);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (9, 3, 5, 3, 3);
INSERT INTO box (id, owner_id, storage_room_id, x, y) VALUES (10, 3, 5, 1, 1);

INSERT INTO box_categories (box_id, categories) VALUES (1, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (1, 'LIGHT');
INSERT INTO box_categories (box_id, categories) VALUES (2, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (2, 'LIGHT');
INSERT INTO box_categories (box_id, categories) VALUES (3, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (4, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (5, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (6, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (7, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (8, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (9, 'FRAGILE');
INSERT INTO box_categories (box_id, categories) VALUES (10, 'FRAGILE');

INSERT INTO box_materials (box_id, materials) VALUES (1, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (2, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (3, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (4, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (5, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (6, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (7, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (8, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (9, 'GLASS');
INSERT INTO box_materials (box_id, materials) VALUES (10, 'GLASS');