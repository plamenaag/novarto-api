INSERT IGNORE INTO payment_status (id, code)
VALUES (1, "DUE");
INSERT IGNORE INTO payment_status (id, code)
VALUES (2, "PAID");
INSERT IGNORE INTO account_status(id, code)
VALUES (1, "ACTIVE");
INSERT IGNORE INTO account_status(id, code)
VALUES (2, "INACTIVE");
INSERT IGNORE INTO subscription (id, name,price,channel_count,video_quality)
VALUES (1, "BASIC",15,150,"720p@30fps");
INSERT IGNORE INTO subscription (id, name,price,channel_count,video_quality)
VALUES (2, "REGULAR",25,250,"720p@60fps");
INSERT IGNORE INTO subscription (id, name,price,channel_count,video_quality)
VALUES (3, "PLATINUM",35,300,"1080p@60fps");