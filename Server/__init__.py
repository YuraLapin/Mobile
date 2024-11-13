from flask import Flask, jsonify, request
app = Flask(__name__)

CRONTAB_FILE_LOCATION = "crontabs"
GPIO_FILE_LOCATION = "gpio/gpio24"
days = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"]

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

@app.route('/check_interval', methods=["POST", "GET"])
def check_status():
    f = open(GPIO_FILE_LOCATION, "r")
    res = f.read()
    f.close()
    return res