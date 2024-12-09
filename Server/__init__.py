from flask import Flask, jsonify, request
import json
app = Flask(__name__)

CRONTAB_FILE_LOCATION = "crontabs"
GPIO_FILE_LOCATION = "gpio/gpio24"
JSON_FILE_LOCATION = "intervals.json"

days = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"]




def init():
    try:
        f = open(JSON_FILE_LOCATION, 'r')
        raw_json = f.read()
        f.close()
        try:
            parsed_intervals = json.loads(raw_json)
        except json.decoder.JSONDecodeError:
            parsed_intervals = []
        for interval in parsed_intervals:
            on_line = create_crontab_line(interval["day_of_week_on"], interval["hour_on"], interval["minute_on"], True)
            off_line = create_crontab_line(interval["day_of_week_off"], interval["hour_off"], interval["minute_off"], False)
            write_on = True
            write_off = True
            f = open(CRONTAB_FILE_LOCATION, "r+")
            for line in f.readlines():
                if line.strip("\n") == on_line.strip("\n"):
                    write_on = False
                elif line.strip("\n") == off_line.strip("\n"):
                    write_off = False
            if write_on:
                f.write(on_line)
            if write_off:
                f.write(off_line)
            f.close()

    except FileNotFoundError:
        print(f"intervals.json not found at {JSON_FILE_LOCATION}")



def create_crontab_line(day_of_week, hour, minute, on):
    crontab_line = "\n\n"
    crontab_line += minute + " "
    crontab_line += hour + " "
    crontab_line += "* "
    crontab_line += "* "

    if day_of_week in days:
        crontab_line += day_of_week + " "
    else:
        crontab_line += days[int(day_of_week)] + " "

    if (on):
        crontab_line += "python enable_security.py"
    else:
        crontab_line += "python disable_security.py"

    return crontab_line



@app.route('/add_interval', methods=["POST"])
def add_interval():
    day_of_week_on = request.values['day_of_week_on']
    hour_on = request.values['hour_on']
    minute_on = request.values['minute_on']
    day_of_week_off = request.values['day_of_week_off']
    hour_off = request.values['hour_off']
    minute_off = request.values['minute_off']

    if day_of_week_on not in days and (int(day_of_week_on) < 1 or int(day_of_week_on) > 7):
        return "Invalid day_of_week_on", 400
    
    if day_of_week_off not in days and (int(day_of_week_off) < 1 or int(day_of_week_off) > 7):
        return "Invalid day_of_week_off", 400
    
    if int(hour_on) > 23 or int(hour_on) < 0:
        return "Invalid hour_on", 400
    
    if int(hour_off) > 23 or int(hour_off) < 0:
        return "Invalid hour_off", 400
    
    if int(minute_on) > 59 or int(minute_on) < 0:
        return "Invalid minute_on", 400
    
    if int(minute_off) > 59 or int(minute_off) < 0:
        return "Invalid minute_off", 400

    try:
        f = open(JSON_FILE_LOCATION, 'r')
        raw_json = f.read()
        f.close()
    except FileNotFoundError:
        raw_json = '[]'

    try:
        parsed_intervals = json.loads(raw_json)
    except json.decoder.JSONDecodeError:
        parsed_intervals = []
    interval = {"day_of_week_on": day_of_week_on, "hour_on": hour_on, "minute_on": minute_on, "day_of_week_off": day_of_week_off, "hour_off": hour_off, "minute_off": minute_off}
    if interval not in parsed_intervals:
        parsed_intervals.append(interval)
        raw_json = json.dumps(parsed_intervals)
        f = open(JSON_FILE_LOCATION, 'w')
        f.write(raw_json)
        f.close

    on_line = create_crontab_line(day_of_week_on, hour_on, minute_on, True)
    off_line = create_crontab_line(day_of_week_off, hour_off, minute_off, False)

    write_on = True
    write_off = True

    f = open(CRONTAB_FILE_LOCATION, "r+")
    for line in f.readlines():
        if line.strip("\n") == on_line.strip("\n"):
            write_on = False
        elif line.strip("\n") == off_line.strip("\n"):
            write_off = False

    if write_on:
        f.write(on_line)

    if write_off:
        f.write(off_line)

    f.close()

    resp = jsonify(success=True)
    return resp



@app.route('/delete_interval', methods=["POST"])
def delete_interval():
    day_of_week_on = request.values['day_of_week_on']
    hour_on = request.values['hour_on']
    minute_on = request.values['minute_on']
    day_of_week_off = request.values['day_of_week_off']
    hour_off = request.values['hour_off']
    minute_off = request.values['minute_off']

    if day_of_week_on not in days and (int(day_of_week_on) < 1 or int(day_of_week_on) > 7):
        return "Invalid day_of_week_on", 400
    
    if day_of_week_off not in days and (int(day_of_week_off) < 1 or int(day_of_week_off) > 7):
        return "Invalid day_of_week_off", 400
    
    if int(hour_on) > 23 or int(hour_on) < 0:
        return "Invalid hour_on", 400
    
    if int(hour_off) > 23 or int(hour_off) < 0:
        return "Invalid hour_off", 400
    
    if int(minute_on) > 59 or int(minute_on) < 0:
        return "Invalid minute_on", 400
    
    if int(minute_off) > 59 or int(minute_off) < 0:
        return "Invalid minute_off", 400

    try:
        f = open(JSON_FILE_LOCATION, 'r')
        raw_json = f.read()
        f.close()
    except FileNotFoundError:
        raw_json = '[]'

    try:
        old_intervals = json.loads(raw_json)
    except json.decoder.JSONDecodeError:
        old_intervals = []
    new_intervals = []
    interval_to_delete = {"day_of_week_on": day_of_week_on, "hour_on": hour_on, "minute_on": minute_on, "day_of_week_off": day_of_week_off, "hour_off": hour_off, "minute_off": minute_off}
    for interval in old_intervals:
        if interval != interval_to_delete:
            new_intervals.append(interval)

    raw_json = json.dumps(new_intervals)
    f = open(JSON_FILE_LOCATION, 'w')
    f.write(raw_json)
    f.close

    on_line = create_crontab_line(day_of_week_on, hour_on, minute_on, True)
    off_line = create_crontab_line(day_of_week_off, hour_off, minute_off, False)

    f = open(CRONTAB_FILE_LOCATION, "r")
    lines = f.readlines()
    f.close()

    f = open(CRONTAB_FILE_LOCATION, "w")
    for line in lines:
        if line.strip("\n") != on_line.strip("\n") and line.strip("\n") != off_line.strip("\n"):
            f.write(line)
    f.close()

    f = open(CRONTAB_FILE_LOCATION, "r")
    stripped_file = f.read().rstrip("\n")
    f.close()
    
    f = open(CRONTAB_FILE_LOCATION, "w")
    f.write(stripped_file)
    f.close

    resp = jsonify(success=True)
    return resp



@app.route('/check_status', methods=["GET"])
def check_status():
    f = open(GPIO_FILE_LOCATION, "r")
    res = f.read()
    f.close()
    return res



@app.route('/get_intervals', methods=["GET"])
def get_intervals():
    f = open(JSON_FILE_LOCATION, 'r')
    raw_json = f.read()
    f.close()
    try:
        parsed_intervals = json.loads(raw_json)
    except json.decoder.JSONDecodeError:
        parsed_intervals = []
    return parsed_intervals




init()
