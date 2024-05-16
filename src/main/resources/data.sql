insert into products (name, price, stock_count, size, color, created_at, updated_at)
values ('후드티', 50000, 30, 'xl', 'white', '2024-04-01 12:00:00', '2024-04-01 12:00:00');


insert into product_stocks (product_id, stock_count, created_at, updated_at)
values (1, 30, '2024-04-01 12:00:00', '2024-04-01 12:00:00');


insert into users (name, address, phone_number, point, created_at, updated_at)
values ('김 아무개', '블라블라', '010-1234-5678', 100000, '2024-04-01 12:00:00', '2024-04-01 12:00:00');


insert into orders (user_id, pay_amount, receiver_name, address, phone_number, order_status, ordered_at, created_at)
values (1, 10000, '김 아무개', '블라블라', '010-1234-4321', 'PAID', '2024-04-01 20:00:00', '2024-04-01 20:00:00');


insert into order_items (order_id, product_id, product_name, unit_price, total_price, quantity, created_at, updated_at)
values (1, 1, '후드티', 50000, 50000, 1, '2024-04-01 20:00:00', '2024-04-01 20:00:00');


insert into payments (order_id, pay_amount, payment_method, created_at, updated_at)
values (1, 126000, 'CARD', '2024-04-01 20:00:00', '2024-04-01 20:00:00');