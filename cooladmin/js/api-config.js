const API_BASE_URL =
    window.location.hostname === "localhost" ||
    window.location.hostname === "127.0.0.1"
        ? "http://localhost:8080"
        : "https://alumniconnect-87mx.onrender.com";

// Automatically redirect old localhost API requests
// to the correct backend when running online.
const originalFetch = window.fetch.bind(window);

window.fetch = function (input, init) {
    if (typeof input === "string") {
        input = input.replace(
            "http://localhost:8080",
            API_BASE_URL
        );
    }

    return originalFetch(input, init);
};