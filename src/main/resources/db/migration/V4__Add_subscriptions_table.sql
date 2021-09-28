CREATE TABLE user_subscriptions
(
    channel_id    int8 NOT NULL references users,
    subscriber_id int8 NOT NULL references users,
    primary key (channel_id, subscriber_id)
)