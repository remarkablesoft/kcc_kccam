export function disableLogs() {
	console.log = () => {};
	// or you can override any other stuff you want
}

process.env.NODE_ENV === "production" ? disableLogs() : null;
