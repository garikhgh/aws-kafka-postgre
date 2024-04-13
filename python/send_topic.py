from confluent_kafka import Producer


message_value = "{\"candidateName\": \"Gexapet\", \"votes\": 1}"
# Produce messages
def delivery_report(err, msg):
    if err is not None:
        print(f'Message delivery failed: {err}')
    else:
        print(f'Message delivered to {msg.topic()} [{msg.partition()}]')


p = Producer({'bootstrap.servers':'172.19.0.4:9092'})
p.produce('vote', message_value.encode('utf-8'), callback=delivery_report)
p.flush()