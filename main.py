# Copyright 2016 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import webapp2

class MainPage(webapp2.RequestHandler):
    def get(self):
        # build a list of operations
        f = {'+': lambda x, y: str(float(x) + float(y)),
             '-': lambda x, y: str(float(x) - float(y)),
             '*': lambda x, y: str(float(x) * float(y)),
             '/': lambda x, y: str(float(x) / float(y)),
             'C': lambda x, y: "",
            }
        # get page parameters
        x = self.request.get('x')
        y = self.request.get('y')
        operator = self.request.get('operator')
        # calculate 
        result = ""
        try:
            result = f[operator](x, y)
        except ValueError:
            result = "Error: Incorrect Number"
        except ZeroDivisionError:
            result = "Error: Division by zero"
        except KeyError:
            pass
        # build HTML response
        buttons = "".join(["<input type='submit' name='operator' value='"
                           + o + "'>" for o in sorted(f.keys())])
        self.response.out.write("""<html>
            <body>
            <form action='/' method='get' autocomplete='off'> 
            <input type='text' name='x' value='%s'/><br/>
            <input type='text' name='y'/><br/> 
            %s 
            </form>
            </body>
            </html>""" % (result, buttons))

app = webapp2.WSGIApplication([('/', MainPage)], debug=True)