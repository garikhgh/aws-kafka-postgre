import requests
from time import sleep
from concurrent.futures import ThreadPoolExecutor
import threading

url_create = "http://localhost:8081/add/candidate"
url_vote = "http://localhost:8081/add/vote"
url_delete = "http://localhost:8081/delete/all"
data = "{\"candidateName\": \"Gexapet\", \"votes\": 1}"
header = {"Content-Type":"application/json"}

def send_request():
    response = requests.post(url=url_vote,
                             data=data,
                             headers=header)
    return response.status_code


def delete_dandidateAndHistory():
    r = requests.delete(url=url_delete, headers=header)
    print("All data is deleted, status-code=", r.status_code)


def run_request(range_value):
    for i in range_value:
        status_code = send_request()
        print("Status code for vote is ", status_code, i, "thread ", threading.get_native_id())
        sleep(0.01)
    return status_code    

def creat_candidate():
    candidate = "{ \"id\": 1, \"firstName\": \"Valiko\", \"lastName\" : \"Gve\", \"speechName\": \"Gexapet\", \"votes\": 0}"
    response = requests.post(url=url_create,
                             data=candidate,
                             headers=header)
    print("Candidate is created status-code=", response.status_code)
    return response.status_code
    
def run_request_threaded():
     with ThreadPoolExecutor(max_workers = 5) as executor:
         executor.submit(run_request, range(5000))
         executor.submit(run_request, range(5000))
         executor.submit(run_request, range(5000))
         executor.submit(run_request, range(5000))
         executor.submit(run_request, range(5000))
        #  for _ in range(5):
        #      future = executor.submit(run_request, range(200))
        #      print(future.result())
     

if __name__ == "__main__":
    delete_dandidateAndHistory()
    candidate_status_code = creat_candidate()
    if candidate_status_code == 200:
        print("Candidate is created")
        run_request_threaded()
