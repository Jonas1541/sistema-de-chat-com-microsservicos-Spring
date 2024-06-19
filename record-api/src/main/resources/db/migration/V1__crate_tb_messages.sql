
CREATE TABLE tb_messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id_send BIGINT NOT NULL,
    user_id_receive BIGINT NOT NULL,
    message TEXT NOT NULL
);
