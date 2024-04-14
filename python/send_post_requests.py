import requests
from time import sleep

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


def run_request():
    for i in range(1000):
        status_code = send_request()
        print("Status code for vote is ", status_code, i)
        sleep(0.01)

def creat_candidate():
    candidate = "{ \"id\": 1, \"firstName\": \"Valiko\", \"lastName\" : \"Gve\", \"speechName\": \"Gexapet\", \"votes\": 0}"
    response = requests.post(url=url_create,
                             data=candidate,
                             headers=header)
    return response.status_code
    
    


if __name__ == "__main__":
    delete_dandidateAndHistory()
    candidate_status_code = creat_candidate()
    if candidate_status_code == 200:
        print("Candidate is created")
        run_request()
