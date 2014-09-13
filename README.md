Silicon Valley Code Camp
=========

Some code for presentations. 

**jax-rs-rest** Simple project using Jersey to create a GET /hello resource. Tests using Jersey client. To run tests first start the server manually.

Do a GET http://localhost:8080/jax-rs-rest/hello

Send these different headers and see the difference (one at a time).<br/>
Accept:text/plain<br/>
Accept:application/json<br/>
Accept:application/xml<br/>


**jax-rs-rest-spring** More involved, uses Spring to initilize and adds dependencies to [hello-spring-annotation](https://github.com/rahulaga/hello-spring-annotation)

Do a GET http://localhost:8080/jax-rs-rest-spring/hello?name=<>

You can exclude name or send name=hindi or name=english and see what happens.

**osgidemo** and **springosgidemo** are [OSGi and Spring demos](http://www.irahul.com/2009/09/) from more than a few years ago!
