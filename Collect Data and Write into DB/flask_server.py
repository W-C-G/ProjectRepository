#!/usr/bin/env python
# coding: utf-8

# In[ ]:


from flask import Flask, request
import Write_Review_Restaurant as wrr
import Collect_Review_Restaurant as crr

app = Flask(__name__)

@app.route('/')
def hello():
    return '<h1>This is Basic Page Flask</h1>'

@app.route('/writeDB/', methods=['GET'])
def linkWrite():
    address = request.args['address']
    uno = request.args['uno']
    print('address: ', address)
    crr.collect_review(address)
    print('uno: ', uno)
    wrr.writeDB(uno)
    return '<p>hello</p>'

if __name__ == '__main__':
    app.run()

