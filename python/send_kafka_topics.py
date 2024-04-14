from locust import HttpUser, task, between
from confluent_kafka import Producer
from confluent_kafka.serialization import StringSerializer
import json
from time import sleep

message_value = "{\"candidateName\": \"Gexapet\", \"votes\": 1}"

class KafkaUser():
    wait_time = between(1, 2)

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.bootstrap_servers = 'localhost:9092'
        self.topic = 'vote'
        self.serializer = StringSerializer('utf_8')


    def on_start(self):
        self.producer = Producer({
            'bootstrap.servers': self.bootstrap_servers,
            'acks': 'all',
        })

    def on_stop(self):
        self.producer.flush()

    @task
    def send_message(self):
        message = {"candidateName": "Gexapet", "votes": 1}
        self.producer.produce(self.topic, key='consuming', value=json.dumps(message))
        self.on_stop()
        

    def teardown(self):
        self.producer.flush()
        self.producer.close()

kafka = KafkaUser()
for i in range(1000):
    kafka.on_start()
    kafka.send_message()
    print("Sending data to Kafka broker counter=" , i)
    sleep(0.01)



