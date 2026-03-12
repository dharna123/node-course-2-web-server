from flask import Flask
import os
app=Flask(__name__)

@app.route("/")
def home():
    app_name=os.environ['APP_NAME']

    return f"Welcome to {app_name}"

if __name__=="__main__":
    app.run(debug=True)