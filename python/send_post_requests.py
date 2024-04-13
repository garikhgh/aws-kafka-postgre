import requests


url_create = "http://localhost:8081/add/candidate"
url_vote = "http://localhost:8081/add/vote"
data = "{\"candidateName\": \"Gexapet\", \"votes\": 1}"

def send_request():
    response = requests.post(url=url_vote,
                             data=data,
                             headers={"Content-Type":"application/json"})
    return response.status_code
def run_request():
    for i in range(1000):
        status_code = send_request()
        print("Status code for vote is ", status_code, i)

def creat_candidate():
    candidate = "{ \"id\": 1, \"firstName\": \"Valiko\", \"lastName\" : \"Gve\", \"speechName\": \"Gexapet\", \"votes\": 0}"
    response = requests.post(url=url_create,
                             data=candidate,
                             headers={"Content-Type":"application/json"})
    return response.status_code
    
    


if __name__ == "__main__":
    candidate_status_code = creat_candidate()
    if candidate_status_code == 200:
        print("Candidate is created")
        # run_request()
