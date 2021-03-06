import React from 'react';

export function isError(error) {
    if (error === null) return null;
    return (
        <div className="errorOuter">
            <strong className="errorInner">Error!</strong>{error}
        </div>
    );
}

export function progress() {
    return (
        <div className="progress-outer-loc" >
            <div className="progress-inner-loc"></div>
        </div>
    );
}

export function fetchJson(url) {
    return fetch(url)
    .then( res =>{
        if (!res.ok)
            throw new Error(`response status ${res.status}`);
        return res.json();
    });
}

export function fetchJson2(url,postData,method="POST") {

    let b= null;

    if (postData!==null) {
        b= {
            method: method,
            headers: {
              'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(postData)
        };
    }

    return (b===null ? fetch(url) : fetch(url,b))
    .then( res =>{
        if (!res.ok)
            throw new Error(`response status ${res.status}`);
        return res.json();
    });
}

