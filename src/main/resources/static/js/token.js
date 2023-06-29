
const token = searchParam('token')

if(token) {
    localStorage.setItem("access_token", token)
}

function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
}


const testBtn = document.getElementById("testBtn");
const testP = document.getElementById("testP");
const testEmailBtn = document.getElementById("testEmailBtn");
const testEmail = document.getElementById("testEmail");
if(testBtn) {
    testBtn.addEventListener("click", (event) => {
        body = JSON.stringify({
            test: "test"
        });
        function success() {
            testP.innerHTML = '<div style=\"color:blue\">성공<div>';
        }
        function fail() {
            testP.innerHTML = '<div style=\"color:blue\">실패<div>';
        }

        httpRequest("POST", "/api/test", body, success, fail);
    });
};

if(testEmailBtn) {
    testEmailBtn.addEventListener("click", (event) => {
        body = JSON.stringify({
            refreshToken : getCookie("refresh_token")
        });
        function success(res) {
            testEmail.innerHTML = '<div style=\"color:blue\">${res.userInfo.email}<div>'
        }
        function fail() {
            testEmail.innerHTML = '<div style=\"color:blue\"><div>'
        }
        httpRequest("POST", "/api/userInfo", body, success, fail)
    })
    ;
}


// 쿠키를 가져오는 함수
function getCookie(key) {
    var result = null;
    var cookie = document.cookie.split(";");
    cookie.some(function (item) {
        item = item.replace(" ", "");
        var dic = item.split("=");

        if (key === dic[0]) {
            result = dic[1];
            return true;
        }
    });

    return result;
}

// HTTP 요청을 보내는 함수
function httpRequest(method, url, body, success, fail) {
    fetch(url, {
        method: method,
        headers: {
            // 로컬 스토리지에서 액세스 토큰 값을 가져와 헤더에 추가
            Authorization: "Bearer " + localStorage.getItem("access_token"),
            "Content-Type": "application/json",
        },
        body: body,
    }).then((response) => {
        if(response.status === 200 || response.status === 201) {
            return success(response);
        }
        const refresh_token = getCookie("refresh_token");
        if(response.status === 401 && refresh_token) {
            fetch("/api/token", {
                method: "POST",
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("access_token"),
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    refreshToken : getCookie("refresh_token"),
                }),
            })
                .then((res) => {
                    if(res.ok) {
                        return res.json();
                    }
                })
                .then((result) => {
                    // 재발급이 성공하면 로컬 스토리키 값을 새로운 액세스 토큰으로 교체
                    localStorage.setItem("access_token", result.access_token);
                    httpRequest(method, url, body, success, fail);
                })
                .catch((error) => fail());
        } else {
            return fail();
        }
    });
}