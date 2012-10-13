Academic-Journal-System
=======================

Template to generate A Journal Style System Handling Publication and a Peer Review System




Implementation:  


The Controller is  the first instance that is 
accessed when entering a particular web page and getting to know how to pass information from 
the controller to the view was one of our first challenges. We tried to use JSON in the AJAX 
responses, because it is how we had worked with JQUERY before; this was not an easy task and 
in the end we had to discard this approach, since SpringMVC doesn’t offer JSON or XML 
responses. There is a way to get them working but it is far from ideal. 


We ensured AJAX requests were but simple strings, and for form submitting, we implemented 
the response as the standard ModelAndView object that will be received in the JSP using the 
normal ${response}code. Any kind of object could be received like string, array list, hash map, 
and multiple objects of different types - all couldbe sent from the Controller at the same time. 
For example we could handle these 3 objects in one  response: 
${String} / ${Array} / ${HashMap}. This can be easy manipulated with the use of JSTL. 


At the begging, this was confusing, but by the end  of the project we could appreciate its 
advantages. The main plus being how every method of the model layer could be reused in the 
controller and send the response back to the desired view as an object. 


Another task we faced was the correct distinction between roles, such as access rights and 
functionality available to the present user. We insisted on monitoring user sessions why 
permission checks when applicable. All the controllers will check for credentials before the view 
is rendered to the user. 


A big challenge was forcing author to review, priorto publishing. We had to stop an ‘accepted’ 
article from being published if a user had not completed their peer-reviews; a lot of restrictions 
had to be put in place, as we must ensure their reviews are accepted first. To ensure credible 
reviews, it was decided that a reviewed article must be published or finally rejected by the editor 
for the review to count. A credit system was implemented to ensure that the author had sufficient 
credit to publish, and an article is not going to be published until sufficient credit was obtained. 


During implementation, we also found problems with  relating an author to an article, if that 
author did not submit the article, i.e. co-authors.Again, we did not want to force all authors to 
sign-up, cause mix-and-match, etc, so this feature was left out.


It was decided that an editor can submit articles to their own journal. We did not want to prevent 
this, but could not find a simple, suitable way forensuring correct practice; we can only aim for 
honesty on the part of the editor. Likewise for thereviewing process – an editor can review, but 
will also then oversee the entire reviewing process. With only one editor present within the 
system, deadlocks may occur. 


We found a problem with crediting. We developed oursystem to credit a reviewer (one of their 
required three reviews) when that reviewed article is published, not when a review is considered 
acceptable by an Editor; this is because the Editorwill only see the reviews once the 3 reviews 
are completed, after that he can decide to Accept the article and sent it to publication or reject it 
for bad scores. The only problem here is that thereare not a sufficient number of submitted 
articles to review and the right amount of reviewers to ensure every article will be reviewed by 3 
users, at the beginning of the system this could create a deadlock and requires quality articles. 


Other big challenges include how to distinguish between the roles of an author that has to do 3 
reviews and a general reviewer that will always be a reviewer; to achieve this we implemented a 
new user called Temporary Reviewer, this means oncean article has been accepted the Author 
changes role to Reviewer, when they finish their contribution of 3 reviews they will be taken 
back to Author and can’t make more reviews. 


Finally there where the requirements where the system has to automatically check for particular 
information like, notifying reviewers to select more reviews, closing reviews after a week of 
been finished and not allowing more changes, keep checking if the author has enough credit to 
publish his article. To achieve these requirements  we decided to trigger this processes 
automatically every time an Editor logs in to the system, as every time a new Issue is published it 
will check the subscription table and notify users that have subscribed to a specific keyword or to 
complete new issues. 