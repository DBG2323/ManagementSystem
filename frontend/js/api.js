const API_BASE = 'http://localhost:8080/api';

const api = {
    login: (username, password) => {
        return fetch(`${API_BASE}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        }).then(res => res.json());
    },

    getStats: () => {
        return fetch(`${API_BASE}/dashboard/stats`).then(res => res.json());
    },

    getUsers: (page = 1, size = 10) => {
        return fetch(`${API_BASE}/users?page=${page}&size=${size}`).then(res => res.json());
    }
};