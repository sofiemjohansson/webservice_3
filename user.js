export const addUser = user => {
    window.localStorage.setItem('user', JSON.stringify(user));
}

export const getUser = () => {
    return JSON.parse(window.localStorage.getItem('user'));
}

export const removeUser = () => {
    window.localStorage.clear();
}
