import unittest
import json

from parameterized import parameterized
from __init__ import app, create_crontab_line, CRONTAB_FILE_LOCATION, JSON_FILE_LOCATION




class TestServer(unittest.TestCase):
    @parameterized.expand([
        ["MON", "17", "0", True, "0 17 * * MON python enable_security.py"],
        ["MON", "17", "30", False, "30 17 * * MON python disable_security.py"],
    ])
    def test_create_crontab_line(self, day, hour, minute, on, expected):
        actual = create_crontab_line(day, hour, minute, on).strip("\n")
        self.assertEqual(actual, expected)


    @parameterized.expand([
        ["MON", "17", "0", "MON", "17", "30"],
    ])
    def test_add_interval(self, day_of_week_on, hour_on, minute_on, day_of_week_off, hour_off, minute_off):
        data = {"day_of_week_on": day_of_week_on, "hour_on": hour_on, "minute_on": minute_on, "day_of_week_off": day_of_week_off, "hour_off": hour_off, "minute_off": minute_off}
        with app.test_client() as client:
            client.post('/add_interval', data=data)

        f = open(JSON_FILE_LOCATION, 'r')
        raw_json = f.read()
        f.close()
        parsed_intervals = json.loads(raw_json)
        self.assertTrue({"day_of_week_on": day_of_week_on, "hour_on": hour_on, "minute_on": minute_on, "day_of_week_off": day_of_week_off, "hour_off": hour_off, "minute_off": minute_off} in parsed_intervals)

        f = open(CRONTAB_FILE_LOCATION, 'r')
        lines = f.readlines()
        f.close()
        stripped_lines = (line.strip("\n") for line in lines)
        self.assertTrue(create_crontab_line(day_of_week_on, hour_on, minute_on, True).strip("\n") in stripped_lines)
        self.assertTrue(create_crontab_line(day_of_week_off, hour_off, minute_off, False).strip("\n") in stripped_lines)


    @parameterized.expand([
        ["MON", "17", "0", "MON", "17", "30"],
    ])
    def test_delete_interval(self, day_of_week_on, hour_on, minute_on, day_of_week_off, hour_off, minute_off):
        data = {"day_of_week_on": day_of_week_on, "hour_on": hour_on, "minute_on": minute_on, "day_of_week_off": day_of_week_off, "hour_off": hour_off, "minute_off": minute_off}
        with app.test_client() as client:
            client.post('/delete_interval', data=data)

        f = open(JSON_FILE_LOCATION, 'r')
        raw_json = f.read()
        f.close()
        parsed_intervals = json.loads(raw_json)
        self.assertTrue({"day_of_week_on": day_of_week_on, "hour_on": hour_on, "minute_on": minute_on, "day_of_week_off": day_of_week_off, "hour_off": hour_off, "minute_off": minute_off} not in parsed_intervals)

        f = open(CRONTAB_FILE_LOCATION, 'r')
        lines = f.readlines()
        f.close()
        stripped_lines = (line.strip("\n") for line in lines)
        self.assertTrue(create_crontab_line(day_of_week_on, hour_on, minute_on, True).strip("\n") not in stripped_lines)
        self.assertTrue(create_crontab_line(day_of_week_off, hour_off, minute_off, False).strip("\n") not in stripped_lines)




if __name__ == "__main__":
    unittest.main()