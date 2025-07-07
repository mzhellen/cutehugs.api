CREATE TABLE order_items(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	quantity INT,
	sub_total DECIMAL,
	order_id BIGINT,
	product_id BIGINT,
	FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE,
	FOREIGN KEY(product_id) REFEREnCES products(id) ON DELETE CASCADE
);