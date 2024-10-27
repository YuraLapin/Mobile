from flask import Flask
app = Flask(__name__)

@app.route('/add_interval', methods=['POST'])
def add_interval(start, end):
    print('add_interval')

@app.route('/delete_interval', methods=['POST'])
def delete_interval(start, end):
    print('delete_interval')