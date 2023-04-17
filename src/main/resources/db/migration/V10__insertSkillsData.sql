INSERT INTO skills (skillName, parentId) VALUES ('Testing', null);
INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Others', (SELECT id FROM skills WHERE skillName = 'Testing'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Regression testing', (SELECT id FROM skills WHERE skillName = 'Others'));
INSERT INTO skills (skillName, parentId) VALUES ('Functional testing', (SELECT id FROM skills WHERE skillName = 'Others'));
INSERT INTO skills (skillName, parentId) VALUES ('Automation testing', (SELECT id FROM skills WHERE skillName = 'Testing'));
INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Test automation frameworks', (SELECT id FROM skills WHERE skillName = 'Automation testing'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Selenium', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Cypress', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Robot', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Playwright', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Appium', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Cucumber', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Puppeteer', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Nightwatch', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Selenide', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('WebdriverIO', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('RestAssured', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('RestSharp', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('HttpClient', (SELECT id FROM skills WHERE skillName = 'Test automation frameworks'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Programming languages', (SELECT id FROM skills WHERE skillName = 'Automation testing'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Java', (SELECT id FROM skills WHERE skillName = 'Programming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('C#', (SELECT id FROM skills WHERE skillName = 'Programming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Javascript', (SELECT id FROM skills WHERE skillName = 'Programming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Typescript', (SELECT id FROM skills WHERE skillName = 'Programming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Kotlin', (SELECT id FROM skills WHERE skillName = 'Programming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Ruby', (SELECT id FROM skills WHERE skillName = 'Programming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Python', (SELECT id FROM skills WHERE skillName = 'Programming languages'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Reporting tools', (SELECT id FROM skills WHERE skillName = 'Automation testing'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Allure', (SELECT id FROM skills WHERE skillName = 'Reporting tools'));
INSERT INTO skills (skillName, parentId) VALUES ('ReportPortal', (SELECT id FROM skills WHERE skillName = 'Reporting tools'));

INSERT INTO skills (skillName, parentId) VALUES ('Non-functional testing', (SELECT id FROM skills WHERE skillName = 'Testing'));

INSERT INTO skills (skillName, parentId, uniqueSkillIdentifier, subItemsAreSkills) VALUES ('Others', (SELECT id FROM skills WHERE skillName = 'Non-functional testing'), 'Non-functional testingOthers', true);
INSERT INTO skills (skillName, parentId) VALUES ('Compatibility testing', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'Non-functional testingOthers'));
INSERT INTO skills (skillName, parentId) VALUES ('Usability testing', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'Non-functional testingOthers'));
INSERT INTO skills (skillName, parentId) VALUES ('Maintainability testing', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'Non-functional testingOthers'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Performance testing', (SELECT id FROM skills WHERE skillName = 'Non-functional testing'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Jmeter', (SELECT id FROM skills WHERE skillName = 'Performance testing'));
INSERT INTO skills (skillName, parentId) VALUES ('K6', (SELECT id FROM skills WHERE skillName = 'Performance testing'));
INSERT INTO skills (skillName, parentId) VALUES ('Gatling', (SELECT id FROM skills WHERE skillName = 'Performance testing'));
INSERT INTO skills (skillName, parentId) VALUES ('Lighthouse audit', (SELECT id FROM skills WHERE skillName = 'Performance testing'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Security testing', (SELECT id FROM skills WHERE skillName = 'Non-functional testing'), true);
INSERT INTO skills (skillName, parentId) VALUES ('OWASP/OWASP ZAP', (SELECT id FROM skills WHERE skillName = 'Security testing'));
INSERT INTO skills (skillName, parentId) VALUES ('BURP Suite', (SELECT id FROM skills WHERE skillName = 'Security testing'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Accessibility testing', (SELECT id FROM skills WHERE skillName = 'Non-functional testing'), true);
INSERT INTO skills (skillName, parentId) VALUES ('WCAG standards', (SELECT id FROM skills WHERE skillName = 'Accessibility testing'));
INSERT INTO skills (skillName, parentId) VALUES ('JAWS', (SELECT id FROM skills WHERE skillName = 'Accessibility testing'));
INSERT INTO skills (skillName, parentId) VALUES ('VoiceOver', (SELECT id FROM skills WHERE skillName = 'Accessibility testing'));
INSERT INTO skills (skillName, parentId) VALUES ('NVDA', (SELECT id FROM skills WHERE skillName = 'Accessibility testing'));
INSERT INTO skills (skillName, parentId) VALUES ('AXE', (SELECT id FROM skills WHERE skillName = 'Accessibility testing'));




INSERT INTO skills (skillName, parentId) VALUES ('Development', null);

INSERT INTO skills (skillName, parentId) VALUES ('Frameworks/Platforms', (SELECT id FROM skills WHERE skillName = 'Development'));
INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Spring', (SELECT id FROM skills WHERE skillName = 'Frameworks/Platforms'), true);

INSERT INTO skills (skillName, parentId) VALUES ('Spring Boot', (SELECT id FROM skills WHERE skillName = 'Spring'));
INSERT INTO skills (skillName, parentId) VALUES ('Spring for Apache Kafka', (SELECT id FROM skills WHERE skillName = 'Spring'));
INSERT INTO skills (skillName, parentId) VALUES ('Spring Data', (SELECT id FROM skills WHERE skillName = 'Spring'));
INSERT INTO skills (skillName, parentId) VALUES ('Spring Cloud', (SELECT id FROM skills WHERE skillName = 'Spring'));
INSERT INTO skills (skillName, parentId) VALUES ('Spring Web Services', (SELECT id FROM skills WHERE skillName = 'Spring'));
INSERT INTO skills (skillName, parentId) VALUES ('Spring Hateoas', (SELECT id FROM skills WHERE skillName = 'Spring'));


INSERT INTO skills (skillName, parentId) VALUES ('Database', (SELECT id FROM skills WHERE skillName = 'Development'));
INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('SQL', (SELECT id FROM skills WHERE skillName = 'Database'), true);
INSERT INTO skills (skillName, parentId) VALUES ('SQL Server', (SELECT id FROM skills WHERE skillName = 'SQL'));
INSERT INTO skills (skillName, parentId) VALUES ('Oracle', (SELECT id FROM skills WHERE skillName = 'SQL'));
INSERT INTO skills (skillName, parentId) VALUES ('PostgreSQL', (SELECT id FROM skills WHERE skillName = 'SQL'));
INSERT INTO skills (skillName, parentId) VALUES ('My SQL', (SELECT id FROM skills WHERE skillName = 'SQL'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('No SQL', (SELECT id FROM skills WHERE skillName = 'Database'), true);
INSERT INTO skills (skillName, parentId) VALUES ('MongoDB', (SELECT id FROM skills WHERE skillName = 'No SQL'));
INSERT INTO skills (skillName, parentId) VALUES ('CosmosDB', (SELECT id FROM skills WHERE skillName = 'No SQL'));
INSERT INTO skills (skillName, parentId) VALUES ('DynamoDB', (SELECT id FROM skills WHERE skillName = 'No SQL'));
INSERT INTO skills (skillName, parentId) VALUES ('Cassandra SQL', (SELECT id FROM skills WHERE skillName = 'No SQL'));

INSERT INTO skills (skillName, parentId, uniqueSkillIdentifier, subItemsAreSkills) VALUES ('Others', (SELECT id FROM skills WHERE skillName = 'Database'), 'DatabaseOthers', true);
INSERT INTO skills (skillName, parentId) VALUES ('Redis', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DatabaseOthers'));
INSERT INTO skills (skillName, parentId) VALUES ('ElasticSearch', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DatabaseOthers'));

INSERT INTO skills (skillName, parentId, uniqueSkillIdentifier, subItemsAreSkills) VALUES ('Programming languages', (SELECT id FROM skills WHERE skillName = 'Development'), 'DevelopmentProgramming languages', true);
INSERT INTO skills (skillName, parentId) VALUES ('Java', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('C#', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Javascript', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Typescript', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Python', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Kotlin', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Ruby', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Go', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Swift', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('C', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));
INSERT INTO skills (skillName, parentId) VALUES ('C++', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'DevelopmentProgramming languages'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Scripting languages', (SELECT id FROM skills WHERE skillName = 'Development'), true);
INSERT INTO skills (skillName, parentId) VALUES ('PowerShell', (SELECT id FROM skills WHERE skillName = 'Scripting languages'));
INSERT INTO skills (skillName, parentId) VALUES ('Bash', (SELECT id FROM skills WHERE skillName = 'Scripting languages'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Modern Design Patterns and Approaches', (SELECT id FROM skills WHERE skillName = 'Development'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Microservices', (SELECT id FROM skills WHERE skillName = 'Modern Design Patterns and Approaches'));
INSERT INTO skills (skillName, parentId) VALUES ('Event Sourcing', (SELECT id FROM skills WHERE skillName = 'Modern Design Patterns and Approaches'));
INSERT INTO skills (skillName, parentId) VALUES ('Continues Development', (SELECT id FROM skills WHERE skillName = 'Modern Design Patterns and Approaches'));
INSERT INTO skills (skillName, parentId) VALUES ('A/B testing', (SELECT id FROM skills WHERE skillName = 'Modern Design Patterns and Approaches'));
INSERT INTO skills (skillName, parentId) VALUES ('Data Streaming', (SELECT id FROM skills WHERE skillName = 'Modern Design Patterns and Approaches'));
INSERT INTO skills (skillName, parentId) VALUES ('Messaging', (SELECT id FROM skills WHERE skillName = 'Modern Design Patterns and Approaches'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Unit testing frameworks', (SELECT id FROM skills WHERE skillName = 'Development'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Xunit', (SELECT id FROM skills WHERE skillName = 'Unit testing frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Nunit', (SELECT id FROM skills WHERE skillName = 'Unit testing frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('Junit', (SELECT id FROM skills WHERE skillName = 'Unit testing frameworks'));
INSERT INTO skills (skillName, parentId) VALUES ('TestNG', (SELECT id FROM skills WHERE skillName = 'Unit testing frameworks'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Building tools', (SELECT id FROM skills WHERE skillName = 'Development'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Maven', (SELECT id FROM skills WHERE skillName = 'Building tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Gradle', (SELECT id FROM skills WHERE skillName = 'Building tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Ant', (SELECT id FROM skills WHERE skillName = 'Building tools'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Bundlers', (SELECT id FROM skills WHERE skillName = 'Development'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Webpack', (SELECT id FROM skills WHERE skillName = 'Bundlers'));
INSERT INTO skills (skillName, parentId) VALUES ('Gulp', (SELECT id FROM skills WHERE skillName = 'Bundlers'));
INSERT INTO skills (skillName, parentId) VALUES ('Babel', (SELECT id FROM skills WHERE skillName = 'Bundlers'));
INSERT INTO skills (skillName, parentId) VALUES ('Parcel', (SELECT id FROM skills WHERE skillName = 'Bundlers'));
INSERT INTO skills (skillName, parentId) VALUES ('Browserify', (SELECT id FROM skills WHERE skillName = 'Bundlers'));
INSERT INTO skills (skillName, parentId) VALUES ('Grunt', (SELECT id FROM skills WHERE skillName = 'Bundlers'));


INSERT INTO skills (skillName, parentId) VALUES ('DevOps', null);

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Cloud', (SELECT id FROM skills WHERE skillName = 'DevOps'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Azure cloud', (SELECT id FROM skills WHERE skillName = 'Cloud'));
INSERT INTO skills (skillName, parentId) VALUES ('AWS cloud', (SELECT id FROM skills WHERE skillName = 'Cloud'));
INSERT INTO skills (skillName, parentId) VALUES ('Google cloud', (SELECT id FROM skills WHERE skillName = 'Cloud'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('OS', (SELECT id FROM skills WHERE skillName = 'DevOps'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Linux', (SELECT id FROM skills WHERE skillName = 'OS'));
INSERT INTO skills (skillName, parentId) VALUES ('Windows', (SELECT id FROM skills WHERE skillName = 'OS'));
INSERT INTO skills (skillName, parentId) VALUES ('Unix', (SELECT id FROM skills WHERE skillName = 'OS'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('DevOps practices', (SELECT id FROM skills WHERE skillName = 'DevOps'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Infrastructure as a code', (SELECT id FROM skills WHERE skillName = 'DevOps practices'));
INSERT INTO skills (skillName, parentId) VALUES ('Delivery pipelines', (SELECT id FROM skills WHERE skillName = 'DevOps practices'));
INSERT INTO skills (skillName, parentId) VALUES ('CI/CD', (SELECT id FROM skills WHERE skillName = 'DevOps practices'));
INSERT INTO skills (skillName, parentId) VALUES ('Observability', (SELECT id FROM skills WHERE skillName = 'DevOps practices'));
INSERT INTO skills (skillName, parentId) VALUES ('Configuration management', (SELECT id FROM skills WHERE skillName = 'DevOps practices'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Monitoring and Observability tools', (SELECT id FROM skills WHERE skillName = 'DevOps'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Prometheus', (SELECT id FROM skills WHERE skillName = 'Monitoring and Observability tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Datadog', (SELECT id FROM skills WHERE skillName = 'Monitoring and Observability tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Zabbix', (SELECT id FROM skills WHERE skillName = 'Monitoring and Observability tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Monit', (SELECT id FROM skills WHERE skillName = 'Monitoring and Observability tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Splunk', (SELECT id FROM skills WHERE skillName = 'Monitoring and Observability tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Nagios', (SELECT id FROM skills WHERE skillName = 'Monitoring and Observability tools'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('CI/CD pipeline tools', (SELECT id FROM skills WHERE skillName = 'DevOps'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Jenkins', (SELECT id FROM skills WHERE skillName = 'CI/CD pipeline tools'));
INSERT INTO skills (skillName, parentId) VALUES ('GitLab', (SELECT id FROM skills WHERE skillName = 'CI/CD pipeline tools'));
INSERT INTO skills (skillName, parentId) VALUES ('GitHub Action', (SELECT id FROM skills WHERE skillName = 'CI/CD pipeline tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Azure DevOps', (SELECT id FROM skills WHERE skillName = 'CI/CD pipeline tools'));
INSERT INTO skills (skillName, parentId) VALUES ('Teamcity', (SELECT id FROM skills WHERE skillName = 'CI/CD pipeline tools'));
INSERT INTO skills (skillName, parentId) VALUES ('CircleCI', (SELECT id FROM skills WHERE skillName = 'CI/CD pipeline tools'));
INSERT INTO skills (skillName, parentId) VALUES ('AWS pipelines', (SELECT id FROM skills WHERE skillName = 'CI/CD pipeline tools'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Artifact management solutions', (SELECT id FROM skills WHERE skillName = 'DevOps'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Artifactory', (SELECT id FROM skills WHERE skillName = 'Artifact management solutions'));
INSERT INTO skills (skillName, parentId) VALUES ('Sonatype Nexus', (SELECT id FROM skills WHERE skillName = 'Artifact management solutions'));

INSERT INTO skills (skillName, parentId) VALUES ('Container Orchestration', (SELECT id FROM skills WHERE skillName = 'DevOps'));
INSERT INTO skills (skillName, parentId, uniqueSkillIdentifier, subItemsAreSkills) VALUES ('Others', (SELECT id FROM skills WHERE skillName = 'Container Orchestration'), 'Container OrchestrationOthers', true);
INSERT INTO skills (skillName, parentId) VALUES ('Openshift', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'Container OrchestrationOthers'));
INSERT INTO skills (skillName, parentId) VALUES ('Docker', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'Container OrchestrationOthers'));
INSERT INTO skills (skillName, parentId) VALUES ('Docker Swarm', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'Container OrchestrationOthers'));
INSERT INTO skills (skillName, parentId) VALUES ('Service Fabric', (SELECT id FROM skills WHERE uniqueSkillIdentifier = 'Container OrchestrationOthers'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Kubernetes', (SELECT id FROM skills WHERE skillName = 'Container Orchestration'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Helm', (SELECT id FROM skills WHERE skillName = 'Kubernetes'));
INSERT INTO skills (skillName, parentId) VALUES ('Kubectl', (SELECT id FROM skills WHERE skillName = 'Kubernetes'));
INSERT INTO skills (skillName, parentId) VALUES ('Service Mesh', (SELECT id FROM skills WHERE skillName = 'Kubernetes'));


INSERT INTO skills (skillName, parentId) VALUES ('Business analysis', null);

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Requirement gathering techniques', (SELECT id FROM skills WHERE skillName = 'Business analysis'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Interview', (SELECT id FROM skills WHERE skillName = 'Requirement gathering techniques'));
INSERT INTO skills (skillName, parentId) VALUES ('Lean Requirements Workshop', (SELECT id FROM skills WHERE skillName = 'Requirement gathering techniques'));
INSERT INTO skills (skillName, parentId) VALUES ('User Journey Map', (SELECT id FROM skills WHERE skillName = 'Requirement gathering techniques'));
INSERT INTO skills (skillName, parentId) VALUES ('Service Blueprint', (SELECT id FROM skills WHERE skillName = 'Requirement gathering techniques'));
INSERT INTO skills (skillName, parentId) VALUES ('Competitive Analysis', (SELECT id FROM skills WHERE skillName = 'Requirement gathering techniques'));

INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Requirements visualization', (SELECT id FROM skills WHERE skillName = 'Business analysis'), true);
INSERT INTO skills (skillName, parentId) VALUES ('Draw.io', (SELECT id FROM skills WHERE skillName = 'Requirements visualization'));
INSERT INTO skills (skillName, parentId) VALUES ('Miro', (SELECT id FROM skills WHERE skillName = 'Requirements visualization'));
INSERT INTO skills (skillName, parentId) VALUES ('Confluence', (SELECT id FROM skills WHERE skillName = 'Requirements visualization'));
INSERT INTO skills (skillName, parentId) VALUES ('User Story Map', (SELECT id FROM skills WHERE skillName = 'Requirements visualization'));
INSERT INTO skills (skillName, parentId) VALUES ('UML models', (SELECT id FROM skills WHERE skillName = 'Requirements visualization'));
INSERT INTO skills (skillName, parentId) VALUES ('BPMN models', (SELECT id FROM skills WHERE skillName = 'Requirements visualization'));
INSERT INTO skills (skillName, parentId) VALUES ('Mind Maps', (SELECT id FROM skills WHERE skillName = 'Requirements visualization'));
INSERT INTO skills (skillName, parentId) VALUES ('System Context diagrams', (SELECT id FROM skills WHERE skillName = 'Requirements visualization'));


INSERT INTO skills (skillName, parentId, subItemsAreSkills) VALUES ('Languages', null, true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('English', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Italian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('German', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('French', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Estonian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Hungarian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Latvian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Lithuanian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Maltese', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Polish', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Slovak', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Slovenian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Dutch', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Danish', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Greek', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Portuguese', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Finnish', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Czech', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Bulgarian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Croatian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Estonian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('French', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Romanian', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Irish', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Swedish', (SELECT id FROM skills WHERE skillName = 'Languages'), true);
INSERT INTO skills (skillName, parentId, isLanguage) VALUES ('Spanish', (SELECT id FROM skills WHERE skillName = 'Languages'), true);







