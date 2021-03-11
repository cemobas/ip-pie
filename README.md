# ip-pie

ip-pie is an API that returns countries on the northern hemisphere, when invoked with asked IP addresses.

## How to run?

It is a simple spring boot application, so it'll run on port 8888, as the app is started.

#### Constraints

It can be invoked with minimum 1, maximum 5 IPs. Any other query params are ignored.

#### Sample valid command

* Has 5 params

`http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0`

* IPs can repeat

`http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.8.8`

#### Sample invalid commands

* Fails for having no params (Code: 400)

`http://localhost:8888/northcountries`

* Fails for having 6 params (Code: 422)

`http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0&ip=190.0.0.0`
