# AlumniConnect Recommendation Evaluation

## Purpose

This document summarises the recommendation calculation tests and the user-based evaluation reported in Chapter Five of the thesis. It distinguishes technical checks of the matching calculation from participants' opinions about recommendation relevance and usefulness.

## 1. Recommendation calculation tests

The recommendation component was checked using alumni profiles with different skill combinations and job opportunities with different required skills. The configured recommendation threshold was 60%.

| Test scenario | Expected / calculated result | Observed result |
|---|---|---|
| Alumnus has all required skills for a vacancy | 100.00% match; no required skills are missing. | **PASS** — The application displayed a 100% match and indicated that all required skills were present. |
| Alumnus has two of three required skills | 66.67% match; one required skill is missing. | **PASS** — The application displayed 67%, with the matching and missing skills listed. |
| Alumnus has three of five required skills | 60.00% match; two required skills are missing. This meets the 60% configured threshold. | **PASS** — The application displayed 60% and included the vacancy in Recommended Jobs. |
| Alumnus has no registered skills | No vacancy should meet the 60.00% recommendation threshold. | **PASS** — No jobs were displayed in Recommended Jobs for the profile without registered skills. |
| Vacancy is inactive or its application deadline has passed | The vacancy is excluded from recommendations. | **PASS** — Inactive and expired job opportunities did not appear in Recommended Jobs. |

The calculation is: **Match percentage = (number of matching required skills / total number of distinct required skills) × 100.**

The application displays matching and missing skills alongside the match percentage. The percentage represents required-skill coverage; it does not represent the probability of employment or guarantee that a vacancy is suitable for an individual.

## 2. User-based evaluation: original analysis

An online questionnaire was used to obtain initial feedback after participants interacted with the system. **Five responses were included in the original analysis documented in the thesis.** Those five participants represented Data Analysis; Engineering; Computer Science and Information Technology; Biology and Biological Sciences; and Social Sciences.

### Reported findings from the first five responses

| Evaluation item | Result reported in the thesis |
|---|---:|
| Participants who considered the available skills sufficient to represent their academic or professional backgrounds | 3 of 5 (60%) |
| Participants who considered the available skills insufficient | 2 of 5 (40%) |
| Average recommendation relevance rating | 2.6 out of 5 |
| Average overall usefulness rating | 2.8 out of 5 |
| Participants who found a recommended job they would realistically consider applying for | 2 of 5 (40%) |
| Participants who answered "Maybe" about applying for a recommended job | 1 of 5 (20%) |
| Participants who did not find a suitable opportunity | 2 of 5 (40%) |
| Participants who confirmed that displayed matching and missing skills were consistent with their profiles and job requirements | 2 of 5 (40%) |
| Participants who could not determine this consistency | 3 of 5 (60%) |

The average recommendation relevance rating of **2.6/5** and average overall usefulness rating of **2.8/5** are two separate measures and should not be treated as interchangeable.


## 3. Feedback and interpretation

The open-ended responses in the original analysis identified a need for a wider range of skills and job opportunities covering different academic and professional disciplines. Participants suggested expanding the available skills, including skills applicable across multiple fields, and increasing the diversity of job recommendations.

The results indicate that correct skill-matching calculations do not automatically guarantee relevant recommendations. Recommendation relevance also depends on the coverage and quality of the skills and job opportunities available in the database.

The original analysis was preliminary and used five responses. It should not be treated as representative of the wider alumni population.

## 4. Questionnaire and response records

The thesis reports that an online questionnaire was used, but it does not reproduce the full questionnaire or the individual participant responses. The original questionnaire and response export should be added to the repository if they are available and can be shared safely. Remove names, email addresses, and other identifying information before publishing the response data.

The questionnaire questions and individual scores have deliberately not been reconstructed in this document. The averages and summary figures above are the results reported in the thesis; individual participant scores should not be inferred from those averages.

## 5. Evaluation limitations

An independent expert-based assessment using relevance labels was not conducted, and Precision@k was not computed. The original user-based analysis used five responses. The findings should therefore be interpreted as initial feedback rather than conclusive evidence of recommendation effectiveness.

