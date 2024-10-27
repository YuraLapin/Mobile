from flask import Flask, jsonify
app = Flask(__name__)

@app.route('/add_interval')
def add_interval():
    f = open("gpio/gpio24", "w")
    f.write("1")
    f.close()
    resp = jsonify(success=True)
    return resp

@app.route('/delete_interval')
def delete_interval():
    f = open("gpio/gpio24", "w")
    f.write("0")
    f.close()
    resp = jsonify(success=True)
    return resp

@app.route('/check_interval')
def check_interval():
    f = open("gpio/gpio24", "r")
    res = f.read()
    f.close()
    return res