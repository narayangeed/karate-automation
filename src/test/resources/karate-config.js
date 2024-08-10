function fn() {
	var env = karate.env
	karate.log(env);
	var host;

	if (!env) {
		host = 'https://reqres.in'
	} else if (env === 'dev') {
		host = "http://usersystem.default.svc"; // Dev environment URL
	} else {
		karate.fail('Karate test failed. Env ' + env + ' not supported');
	}

	var config = {
		baseUrls: host + '/api'
	};

	karate.configure('ssl', true);
	karate.configure('printEnabled', true);
	karate.configure('logPrettyRequest', true);
	karate.configure('logPrettyResponse', true);
	karate.configure('connectTimeout', 5000);
	karate.configure('readTimeout', 10000);
	

	return config;
}