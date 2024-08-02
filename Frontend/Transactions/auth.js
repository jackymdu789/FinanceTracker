export const isAuthenticated = () => {
    return !!sessionStorage.getItem('authToken');
};
