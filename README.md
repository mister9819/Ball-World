3 slip days used.

Author: Soham Pajwani

Link: https://sp91-hw4-collision.herokuapp.com/

##Report: 
###My experience:
I noticed that we have to add a fish, but the ball class did not support this.
Hence, I decided to make a super class of APaintObject from which both the class Ball and class Image can extend.
I even created the class NullObject extend from this parent class.
Next I thought about all the attributes and functions a paint object would require, I add them to the class.
Then I fill out the necessary functions required in the child classes Ball and Image.
While writing one of the behaviors I noticed that only Ball class objects should be colorable, hence I added that feature too.

In HW3, I had made different classes for balls of different behavior. 
But, for this HW I realised I would need too many ball classes for that and that would make it difficult to be extensible.
For that, the behaviors which used variables to keep track of data had to be removed.

Moving onto strategies, since I had removed the different ball classes, I had to remove a couple of strategies.
First, I decided to make different strategies for movement. Soon, I ran out of ideas since, I couldn't use any variables to store temporary data.
Eventually, I realised that I could use a variable inside a strategy! Instead of making a different Ball class.

Next came interaction strategy. At first I made strategies which only took the context of the Ball object who was colliding with other balls.
But then I realised, this was pretty limiting. 
So I created a new interface for collision strategies. 
All the collision strategies inherit from this class.
This allowed me to pass the contexts of both the Ball objects which collide.

Soon, I found this way pretty limiting too. 
If I wanted to use the same strategy for movement and collision, I couldn't!
Hence, I made a new interface IStrategy which my collision and movement strategy extend.
Now, I could make strategies which could be used for collision as well as movement!

Next, I implement switcher objects by giving them a different property name and created a new strategy called switch strategy.
This class is a singleton class which holds the to and from strategy names.
The cmd directory holds the update command and switch strategy command, switch strategy is used by switcher objects.
Update command is used by all the objects.

Fish implementation was pretty simple. 
I just had to give them default collision behavior and any movement behavior which a ball could use.
And I had to make sure it faces the right direction when drawn.

Then I added the ability for the user to pick what kind of strategy objects they would like to remove.
Because of my extensible code deisgn, this was easy to implement.

I had to keep updating the BallWorldStore appropriately throughtout the HW.

###Design patterns:
* I used MVC design pattern for the entire project.

* I used Template Design Pattern for creating objects. Abstract class APaintObject sets up skeleton for classes like Ball, Image & NullObject

* I used Factory Design Pattern for creating objects.

* I used NullObject Design Pattern for creating null object.

* I used Strategy Design Pattern for strategies.
* I used Factory Design Pattern for creating strategies.
* Some of the strategies use Singleton Design Pattern when appropriate.
* I even used composite strategies to create new strategies.

* To generate Factories I used Singleton Design Pattern.

* To keep track of balls I used Observer Design Pattern.

* I used Command Design Pattern for commands.

### Design decisions made to build a robust and extensible solution:
**Model**

Using strategy design pattern and factory design pattern, it's so much easier to make new strategies and let the shapes be able to use it.
A new strategy class needs to be created and inherited from IUpdateStrategy or ICollisionStrategy, depending on the need.
Simply add the strategy to the factory to which it belongs, and the APaintObject classes can directly use them!

Factory design pattern usage in generating Ball objects or Fish objects make it easier to be extensible.

Using the command design pattern means I can quickly create new commands, whether it is the update command or the switch strategy command.
Perhaps, I could add some other commands here as well, like pause.

Having abstract class APaintObject makes it simpler to create any new objects that would need to be drawn on the canvas.
Only need to create the class of that object if required.
Meanwhile, I have also made a generalised Image class, if we need to use an image other than fish for example.

**Controller**

The controller doesn't have any logic. 
It's only job is to redirect requests.
Some requests have parameters which uses passing data privately.

The dispatch adapter doesn't have any logic either. It only passes data between controller and model.

**View**

I tried to make the view as used friendly as I could.
I added dropdowns for strategies because they number in 10s.
And it was not feasible to add button for every option.
This design decision makes it easier to add new strategies to the existing list.

I added buttons where they were appropriate.
I even formatted the CSS a little to make it better looking.
I included a description section of my project if anyone who isn't aware of our class wants to run the demo.


