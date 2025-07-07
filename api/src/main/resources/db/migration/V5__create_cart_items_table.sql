CREATE TABLE cart_items(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	quantity INT,
	sub_total DECIMAL,
	cart_id BIGINT,
	product_id BIGINT,
	FOREIGN KEY(cart_id) REFERENCES carts(id) ON DELETE CASCADE,
	FOREIGN KEY(product_id) REFEREnCES products(id) ON DELETE CASCADE
);