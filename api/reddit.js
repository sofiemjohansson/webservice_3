import axios from 'axios';

const getBaseUrl = () => {
    return process.env.REACT_APP_BASE_URL || process.env.REACT_APP_LOCALHOST;
}

export default axios.create({
    baseURL: getBaseUrl()
});
