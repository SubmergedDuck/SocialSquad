# SkillSquad
CSC207 Project

**Problem domain**

We want to develop a public human resource system for job-seekers and recruiters to find their match. 

**How the system works**
- Job-seekers can upload their resumes through the interface. The application will process the resume to store key 
information such as name, age, education, ect.
- Recruiters have a separate interface, where they can set key information about desired candidates (e.g. education and
experience) and the application will prompt them with potential candidates, based on their uploaded resumes.

**Potential APIs to use**
- https://docs.servicenow.com/en-US/bundle/vancouver-api-reference/page/integrate/inbound-rest/concept/hr-core-api.html

**Potential technical problems blocking progress**
- Format of user-uploaded resume. Because resume formats are diverse, it's possible that our application cannot properly
handle all types, thereby causing some troubles in processing data for storing. We are thinking to put restrictions on
the format of resumes that the users are allowed to upload, or even further, provide them with a web form to fill in
their information, rather than uploading.